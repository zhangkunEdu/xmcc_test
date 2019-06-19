package com.xmcc.repository;

import com.xmcc.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ProductInfoRepository extends JpaRepository<ProductInfo,String> {


     //根据商品的分类目的编号（type）和 状态 （status）查询商品集合
    List<ProductInfo> findProductInfoByProductStatusAndCategoryTypeIn(Integer status,List<Integer>categoryTypeList);


}