package com.xmcc.controller;

import com.lly835.bestpay.model.PayResponse;
import com.xmcc.entity.OrderMaster;
import com.xmcc.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private PayService payService;
    @RequestMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId, @RequestParam("returnUrl") String returnUrl ){

        //根据订单id 获取 订单
        OrderMaster orderMaster = payService.findOrderMasterByOrderId(orderId);

        //根据订单创建支付
        //PayResponse response =
        PayResponse response = payService.create(orderMaster);

        HashMap<String, Object> map = new HashMap<>();
        map.put("payResponse",response);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("weixin/pay",map);

    }

    @RequestMapping("/notify")
    public void weixin_notify(){
        //验证数据，修改订单
        //payService.weixin_notify();

    }

    @RequestMapping("/test")
    public void test(){
        log.info("异步回调OK");
    }

}