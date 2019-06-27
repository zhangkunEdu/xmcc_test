package com.xmcc.controller;

import com.lly835.bestpay.model.PayResponse;
import com.xmcc.entity.OrderMaster;
import com.xmcc.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
        PayResponse response = payService.create(orderMaster);

        log.info("回调的response,{}",response);
        log.info("回调的returnUrl,{}",returnUrl);



        HashMap<String, Object> map = new HashMap<>();
        map.put("payResponse",response);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("weixin/pay",map);


    }

    //支付成功后的回调接口
    @RequestMapping("/notify")
    public ModelAndView weixin_notify(@RequestBody String notifyData){

        log.info("提示:  {用户支付成功,进入回调验证方法}");
        log.info("需要的验证数据为：{}",notifyData);
        //验证数据，修改订单
        payService.weixin_notify(notifyData);
        //返回到页面，页面的内容会被微信读取，告诉微信我们已完成，不会一直发送异步回调
         return new ModelAndView("weixin/success");

    }

    @RequestMapping("/test")
    public void test(){
        log.info("异步回调OK");
    }

}