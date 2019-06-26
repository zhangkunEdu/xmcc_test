package com.xmcc.service;

import com.lly835.bestpay.model.PayResponse;
import com.xmcc.entity.OrderMaster;

public interface PayService {

    //根据id查询订单
    OrderMaster findOrderMasterByOrderId(String orderId);


    //根据订单创建支付
    PayResponse create(OrderMaster orderMaster);


    public void weixin_notify(String notifyData);
}