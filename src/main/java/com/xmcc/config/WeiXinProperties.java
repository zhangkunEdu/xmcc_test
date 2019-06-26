package com.xmcc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WeiXinProperties {
    //公众号id
    private String appid;
    //公众号密码
    private String secret;
    //商户号
    private String mchId;
    //商户秘钥
    private String mchKey;
    //商户证书路径
    private String keyPath;
    //微信异步通知
    private String notifyUrl;
}