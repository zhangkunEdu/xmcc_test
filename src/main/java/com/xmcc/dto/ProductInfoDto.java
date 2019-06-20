package com.xmcc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmcc.entity.ProductInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductInfoDto implements Serializable {

     //@com.fasterxml.jackson.annotation.JsonProperty

    @JsonProperty("id")
    private Integer productId;

    @JsonProperty("name")
    private  String  productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

    //dto转换
    //一般情况都是根据数据库查询到productInfo来构建这个类
    public static ProductInfoDto transfer(ProductInfo productInfo){

        ProductInfoDto infoDto = new ProductInfoDto();
        BeanUtils.copyProperties(productInfo,infoDto);
        return infoDto;
    }

}