package com.xmcc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @Data 相当于  Get Set ToString
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table 指定数据库中是哪一张表
@Table(name = "student")
@Entity
@DynamicUpdate
public class Student implements Serializable {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY )//生成策略 自动增长
    private Integer id;

    private String name;

    private String age;

    private String gender;

    private String address;
}