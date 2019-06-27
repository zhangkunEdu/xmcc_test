package com.xmcc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmcc.entity.OrderDetail;
import com.xmcc.entity.OrderMaster;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("订单参数分页实体类")
@AllArgsConstructor
@NoArgsConstructor
public class OrderMasterPageDto extends OrderMaster implements Serializable {

     //用于保存 订单项
     @JsonProperty("orderDetailList")
     //避免为null时被忽略
     public List<OrderDetail> orderDetailList;

     public static OrderMasterPageDto transfer(OrderMaster orderMaster){
        OrderMasterPageDto dto = new OrderMasterPageDto();
        BeanUtils.copyProperties(orderMaster,dto);
        return dto;
    }

}