package com.jinnian.channel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @author:liuqi
 * @create:2019-04-26 09:03
 * @Description: 拦截器
 * @Version 1.0
 **/
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private OpenIdValue openIdValue;

    /**
     * 自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(openIdValue).addPathPatterns("/channel/register");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/upload/**").addResourceLocations("file:C://upload/");


    }
}
