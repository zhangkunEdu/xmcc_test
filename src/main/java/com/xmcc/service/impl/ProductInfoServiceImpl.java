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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductCategoryRepository categoryRepository;

    @Resource
    private ProductInfoRepository infoRepository;

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
     * 1、把productCategoryDtoList中的每一个productCategoryDto都设置为下一层 foods
     * 2、发现productInfoList就是我们productInfoList, 但实际上我们响应的是productInfoDto
     * 3、所以我们再次对productInfoList进行操作，把productInfo中类目编号（type）与 productCategoryDto中类目编号 一一对应
     * 4、再把把productInfo转为ProductInfoDto
     */
}