package com.jinnian.channel.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @author:liuqi
 * @create:2019-04-26 09:03
 * @Description: 自定义openid拦截器
 * @Version 1.0
 **/
@Component
public class OpenIdValue implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(OpenIdValue.class);

    @Value("${wxappid}")
    private String wxappid;// 公众号的唯一标识
    @Value("${redirect_uri2}")
    private String redirect_uri2;// 授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
    @Value("${response_type}")
    private String response_type;// 返回类型，请填写code
    @Value("${scope}")
    private String scope;//应用授权作用域
    @Value("${state}")
    private String state;//重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
    @Value("${wechat_redirect}")
    private String wechat_redirect;//无论直接打开还是做页面302重定向时候，必须带此参数
    @Value("${secret}")
    private String secret;//公众号的appsecret
    @Value("${grant_type}")
    private String grant_type;//#填写为authorization_code

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //回调地址
        String redirect_uriStr= URLEncoder.encode(redirect_uri2,"UTF-8");

        //拼接参数
        StringBuilder url=new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?"
                +"&appid="+wxappid
                +"&redirect_uri="+redirect_uriStr
                +"&response_type="+response_type
                +"&scope="+scope
                +"&state="+state
                +"&wechat_redirect");

        log.info("微信公众号授权发送,[{}]",url);
        //请不要使用get请求，单纯的跳转即可
        response.sendRedirect(url.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{

    }

}