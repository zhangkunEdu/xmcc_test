package com.xmcc.controller;

import com.xmcc.common.ResultResponse;
import com.xmcc.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("//buyer/product")
//如果想通过Swagger生成接口,需要使用Api
@Api(description = "商品信息接口")
public class ProductInfoController {

    @Resource
    private ProductInfoService productInfoService;

    @RequestMapping("/list")
    @ApiOperation(value = "查询商品列表")
    public ResultResponse list(){


     return productInfoService.QueryProductList();
    }

}