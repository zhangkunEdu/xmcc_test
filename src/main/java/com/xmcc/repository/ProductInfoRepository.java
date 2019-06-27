package com.xmcc.repository;

import com.xmcc.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface  ProductInfoRepository extends JpaRepository<ProductInfo,String> {


     //根据商品的分类目的编号（type）和 状态 （status）查询商品集合
    List<ProductInfo> findByProductStatusAndCategoryTypeIn(Integer status,List<Integer>categoryTypeList);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,value = "update product_info set product_stock=product_stock+?2 where product_id=?1")
    Integer batchUpdate(String productId, Integer quantity);
}