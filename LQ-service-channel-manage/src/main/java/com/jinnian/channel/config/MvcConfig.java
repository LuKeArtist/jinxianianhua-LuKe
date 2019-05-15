package com.jinnian.channel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @description 拦截器注册配置
 * @author liuqi
 * @date 2019/4/28 15:06
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 视图跳转集中配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("login");
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppInterceptor()).addPathPatterns("/**").excludePathPatterns("/login").excludePathPatterns("/toLogin");
    }

}
