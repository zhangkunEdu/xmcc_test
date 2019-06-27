package com.xmcc.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration
public class PayConfig {

    //注入微信配置
    @Resource
    private WeixinProperties weiXinProperties;

    //调用下单业务的Api类（best-pay-sdk第三方jar包）
    @Bean
    public BestPayService bestPayService(){
        //微信公众账号支付配置
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        //设置公众号id
        wxPayH5Config.setAppId(weiXinProperties.getAppid());
        //设置公众号密码
        wxPayH5Config.setAppSecret(weiXinProperties.getSecret());
        //设置商户id
        wxPayH5Config.setMchId(weiXinProperties.getMchId());
        //设置商户密码
        wxPayH5Config.setMchKey(weiXinProperties.getMchKey());
        //设置商户证书路径
        wxPayH5Config.setKeyPath(weiXinProperties.getKeyPath());
        //设置异步通知路径
        wxPayH5Config.setNotifyUrl(weiXinProperties.getNotifyUrl());

        //支付类, 所有方法都在这个类里
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);

        return bestPayService;
    }


}