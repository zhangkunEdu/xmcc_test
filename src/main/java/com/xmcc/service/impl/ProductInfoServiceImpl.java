package com.xmcc.service.impl;

import com.xmcc.common.ResultEnums;
import com.xmcc.common.ResultResponse;
import com.xmcc.dto.ProductCategoryDto;
import com.xmcc.dto.ProductInfoDto;
import com.xmcc.entity.ProductCategory;
import com.xmcc.entity.ProductInfo;
import com.xmcc.repository.ProductCategoryRepository;
import com.xmcc.repository.ProductInfoRepository;
import com.xmcc.service.ProductInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductCategoryRepository categoryRepository;

    @Resource
    private ProductInfoRepository infoRepository;

    /**
     * 根据API格式查询商品列表
     * @return
     */
    /**
     * 分析：
     * 1、把productCategoryDtoList中的每一个productCategoryDto都设置为下一层 foods
     * 2、发现productInfoList就是我们productInfoList, 但实际上我们响应的是productInfoDto
     * 3、所以我们再次对productInfoList进行操作，把productInfo中类目编号（type）与 productCategoryDto中类目编号 一一对应
     * 4、再把把productInfo转为ProductInfoDto
     */
    @Override
    public ResultResponse QueryProductList() {

        //查询所有分类
        List<ProductCategory> allCategoryList = categoryRepository.findAll();

        //把 List<ProductCategory>  转为  List<ProductCategoryDto>
        List<ProductCategoryDto> productCategoryDtoList = allCategoryList.stream()
                .map(productCategory -> ProductCategoryDto.transfer(productCategory))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(allCategoryList)){

            return ResultResponse.fail();
        }

        //获取商品分类 编号 的集合
        List<Integer> categoryTypeList = productCategoryDtoList.stream()
                .map(productCategoryDto -> productCategoryDto.getCategoryType())
                .collect(Collectors.toList());

        //根据 商品分类编号集合 -> 查询商品列表
        List<ProductInfo> productInfoList =
                infoRepository.findByProductStatusAndCategoryTypeIn(ResultEnums.PRODUCT_UP.getCode(), categoryTypeList);

        //对所有分类ProductCategoryDtoList  进行遍历，取出每个商品的类目编号，设置到对应的目录中

        //转为并发流进行遍历
        /**
         * 1、将productinfo设置到foods中
         * 2、过滤、因为不停地商品分类目（type） 进行不同的封装
         * 3、将productInfo 转为 dto
         */
        List<ProductCategoryDto> collect = productCategoryDtoList.parallelStream().map(productCategoryDto -> {
            productCategoryDto.setProductInfoDtoList(productInfoList.stream()
                    .filter(productInfo -> productInfo.getCategoryType() == productCategoryDto.getCategoryType())
                    .map(productInfo -> ProductInfoDto.transfer(productInfo)).collect(Collectors.toList()));
            return productCategoryDto;
        }).collect(Collectors.toList());

        return ResultResponse.success(collect);
    }



    /**
     * 1.根据购物车(订单项) 传来的商品id 查询对应的商品 取得价格等相关信息 如果没查到 订单生成失败
     * 2.比较库存 ，库存不足 订单生成失败
     * 3.生成订单项OrderDetail信息
     * 4.减少商品库存
     * 5.算出总价格 ，组装订单信息 插入数据库得到订单号
     * 6.批量插入订单项
     *
     * 注意:1.生成订单就会减少库存 加入购物车不会  所有的网站基本都是这么设计的
     *      2.商品价格以生成订单时候为准，后面商品价格改变不影响已经生成的订单
     */
    /**
     * 根据商品id 获取商品信息
      * @param productId
     * @return
     */
    @Override
    public ResultResponse<ProductInfo> QueryProductListById(String productId) {
        //判断此id是否为空
        if (StringUtils.isBlank(productId)){
            return ResultResponse.fail(ResultEnums.PARAM_ERROR.getMsg()+":"+productId);
        }
        //1、根据商品id查询对应的商品信息
        Optional<ProductInfo> productInfo = infoRepository.findById(productId);
        //Optional是一个(包装类)，来操作类返回值 用于我们做各种验证
           //如果不存在
            if (!productInfo.isPresent()){
                return ResultResponse.fail(ResultEnums.NOT_EXITS.getMsg());
            }
           //如果存在，则直接取出 ProductInfo
        ProductInfo info = productInfo.get();
            //判断商品是否下架
            if (info.getProductStatus()==ResultEnums.PRODUCT_DOWN.getCode()){
                 return ResultResponse.fail(ResultEnums.PRODUCT_DOWN.getMsg());
            }



        return ResultResponse.success(info);
    }

    /**
     * 保存修改的商品信息
     * @param productInfo
     */
    @Override
    public void updateProduct(ProductInfo productInfo) {
        //修改的信息，直接保存
        infoRepository.save(productInfo);
    }


}