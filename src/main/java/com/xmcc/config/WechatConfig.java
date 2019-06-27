package com.xmcc.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
@Configuration
public class WechatConfig {

    @Resource
    private WeixinProperties weiXinProperties;

    @Bean//文档中需要用到这个对象
    public WxMpService wxMpService(){
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        //设置微信配置的存储
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){

        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        //设置appid  这个在项目中肯定是通过配置来实现
        wxMpInMemoryConfigStorage.setAppId(weiXinProperties.getAppid());
        //设置密码
        wxMpInMemoryConfigStorage.setSecret(weiXinProperties.getSecret());
        return wxMpInMemoryConfigStorage;
    }

 /*   @Resource
    private WeiXinProperties weiXinProperties;


    @Bean//交给Spring管理
    public WxMpService wxMpService(){
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        //设置微信配置的存储
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());

        return wxMpService;
    }


    @Bean//交给Spring管理
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        //设置微信的配置
        wxMpInMemoryConfigStorage.setAppId("wxcec0b9e65c084712");
        wxMpInMemoryConfigStorage.setSecret("05a7e861c1985ced86af77fb8f7163bc");
        return wxMpInMemoryConfigStorage;
    }*/
}