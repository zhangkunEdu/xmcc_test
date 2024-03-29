package com.xmcc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmcc.entity.ProductCategory;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;


@Data
public class ProductCategoryDto implements Serializable {
    //JsonProperty封装成json返回的时候使用 “name”
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    //避免为null时被忽略
    public List<ProductInfoDto> productInfoDtoList;


    //转换dto （把ProductInfo转换成ProductInfoDto）

    public static ProductCategoryDto transfer(ProductCategory productCategory){
        ProductCategoryDto categoryDto = new ProductCategoryDto();
        BeanUtils.copyProperties(productCategory,categoryDto);
        return categoryDto;
    }
}