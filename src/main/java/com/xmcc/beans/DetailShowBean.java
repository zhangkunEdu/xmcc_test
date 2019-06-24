package com.xmcc.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@ApiModel("订单详情实体类")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailShowBean implements Serializable {

    @NotBlank(message = "openid不能为空")
    @ApiModelProperty(value = "用户微信id",dataType = "String")
    private String openid;

    @NotBlank(message = "openid不能为空")
    @ApiModelProperty(value = "商品订单id",dataType = "String")
    private String orderId;

}