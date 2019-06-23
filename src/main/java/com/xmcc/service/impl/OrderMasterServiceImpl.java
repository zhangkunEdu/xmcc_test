package com.xmcc.service.impl;

import com.google.common.collect.Lists;
import com.xmcc.common.*;
import com.xmcc.dto.OrderDetailDto;
import com.xmcc.dto.OrderMasterDto;
import com.xmcc.entity.OrderDetail;
import com.xmcc.entity.OrderMaster;
import com.xmcc.entity.ProductInfo;
import com.xmcc.exception.CustomException;
import com.xmcc.repository.OrderMasterRepository;
import com.xmcc.service.OrderDetailService;
import com.xmcc.service.OrderMasterService;
import com.xmcc.service.ProductInfoService;
import com.xmcc.utils.BigDecimalUtil;
import com.xmcc.utils.IDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Resource
    private ProductInfoService infoService;

    //注入订单项的service
    @Resource
    private OrderDetailService detailService;

    @Resource
    private OrderMasterRepository masterRepository;

    /**
     * 插入订单
     * @param orderMasterDto
     * @return
     */
    @Override
    @Transactional
    public ResultResponse insertOrder(OrderMasterDto orderMasterDto) {
        /**
         * @Valid ：用于配合JSR303注解 验证参数，只能在controller层
         *
         * 如果一定要在在service验证，使用上一个项目中的工具类->BeanValidator
         */
        //1、取出订单项
        List<OrderDetailDto> items = orderMasterDto.getItems();
        //2、创建集合来存储OrderDetail
        List<OrderDetail> orderDetailList  = Lists.newArrayList();
        //3、初始化订单的总金额
        BigDecimal totalPrice = new BigDecimal("0");
        //4、遍历 订单项 获取 商品详情
        for (OrderDetailDto detailDto : items){
            //4.1、查询订单
            ResultResponse<ProductInfo> resultResponse = infoService.QueryProductListById(detailDto.getProductId());
            //4.1、判断productInfoRespose的 code
              //4.1.1、如果返回的是失败的情况,抛出异常 生成订单失败，因为这儿涉及到事务 需要抛出异常 事务机制是遇到异常才会回滚
            if (resultResponse.getCode() == ResultEnums.FAIL.getCode()){
                 throw new CustomException(resultResponse.getMsg());
            }
            //4.2、得到商品
            ProductInfo productInfo = resultResponse.getData();
         //5、比较库存
         if (productInfo.getProductStock()<detailDto.getProductQuantity()){
             throw new CustomException(ProductEnums.PRODUCT_NOT_ENOUGH.getMsg());
         }
         //6、创建订单项
            OrderDetail orderDetail = OrderDetail.builder().detailId(IDUtils.createIdbyUUID()).
                    productId(detailDto.getProductId()).
                    productName(productInfo.getProductName()).
                    productPrice(productInfo.getProductPrice()).
                    productQuantity(detailDto.getProductQuantity()).
                    productIcon(productInfo.getProductIcon()).
                    build();
         //7、把订单项添加到 自定义的orderDetailList集合中
            orderDetailList.add(orderDetail);
         //8、订单添加以后，减少数据库中商品库存
            productInfo.setProductStock(productInfo.getProductStock()-detailDto.getProductQuantity());
            //8.1、设置进数据库,更新商品数据
            infoService.updateProduct(productInfo);
          //9、计算价格
            totalPrice=BigDecimalUtil.add(totalPrice,
                    BigDecimalUtil.multi(productInfo.getProductPrice(),detailDto.getProductQuantity()));

        }

        //对订单进行操作
        //1、生产订单id
        String orderMasterId = IDUtils.createIdbyUUID();
        //2、构建订单信息
        OrderMaster orderMaster = OrderMaster.builder().orderId(orderMasterId).
                buyerName(orderMasterDto.getName()).
                buyerPhone(orderMasterDto.getPhone()).
                buyerAddress(orderMasterDto.getAddress()).
                buyerOpenid(orderMasterDto.getOpenid()).
                orderAmount(totalPrice).
                orderStatus(OrderEnums.NEW.getCode()).
                payStatus(PayEnums.WAIT.getCode()).build();

        //3、生产的订单id，设置到此订单下的订单项中
        List<OrderDetail> collect = orderDetailList.stream().map(orderDetail -> {
            orderDetail.setOrderId(orderMasterId);
            return orderDetail;
        }).collect(Collectors.toList());

        //4、批量插入订单项(到数据库)
        detailService.batchInsert(collect);

        //5、插入订单（到数据库）
        masterRepository.save(orderMaster);

        //6、设置返回的参数

        HashMap<String,String>map =new HashMap<>();
        map.put("orderId",orderMasterId);

        return ResultResponse.success(map);
    }
}