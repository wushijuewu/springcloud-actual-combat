package com.gblfy.apigateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName ZuulConfig
 * @Deacription 动态路由
 * @Author gblfy
 * @Date 2019/4/27 12:34
 * @Version 1.0
 **/
@Component
public class ZuulConfig {

    @ConfigurationProperties("zuul")
    @RefreshScope
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }

}
