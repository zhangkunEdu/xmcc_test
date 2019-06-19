package com.xmcc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

//设置该类为实体类
@Entity
//设置为ture，表示update对象的时候，生成动态的update语句，
// 如果这个字段的值是null就不会被加入到update语句中
@DynamicUpdate(value = true)
//相当于set、get、toString方法
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category")

public class ProductCategory {

    /**商品分类 id**/
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示id自增策略
    private Integer categoryId;

    /**商品分类 名字**/
    private String categoryName;


    /**商品分类 类型**/
    private String categoryType;

    /**创建时间**/
    private Date createTime;


    /**修改时间**/
    private Date updateTime;


}