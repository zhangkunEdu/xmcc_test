package com.xmcc.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 *  分页对象 param
 *
 */
@ApiModel("订单分页实体类")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageBean {

    //从哪页开始
    @Min(value = 0,message = "当前页码不合法！")
    @ApiModelProperty(value = "分页：从哪一页开始",dataType = "int")
    private int page = 0;

    //每页显示条数
    @Min(value = 0,message = "当前页码不合法！")
    @ApiModelProperty(value = "分页：每页显示条数",dataType = "int")
    private int size=10;


    @NotBlank(message = "openid不能为空")
    @ApiModelProperty(value = "用户微信id",dataType = "String")
    private String openid;


    //从哪页开始显示,计算出来的
/*    private int offset = 1;
    public int getOffset() { //offset表示(a) 分页从哪条开始的下一条数据  limit a,b
        return (page-1)*size;
    }*/

/*
    //需要分页的对象
    private List<T> data = new ArrayList<>();*/
}