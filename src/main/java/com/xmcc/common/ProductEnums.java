package com.xmcc.common;

import lombok.Getter;

/**
 * 商品的枚举类
 */
@Getter
public enum  ProductEnums {

    PRODUCT_NOT_ENOUGH(1,"商品库存不足");

    private int code;
    private String msg;


    ProductEnums (int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}