package com.xmcc.service;

import com.xmcc.common.ResultResponse;
import com.xmcc.entity.ProductInfo;


public interface ProductInfoService {

    ResultResponse QueryProductList();
    //通过id查询商品
    ResultResponse<ProductInfo> QueryProductListById(String productId);
    //修改商品
    void updateProduct(ProductInfo productInfo);
}