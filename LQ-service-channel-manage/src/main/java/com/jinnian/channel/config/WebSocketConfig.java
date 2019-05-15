package com.jinnian.channel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yeauty.standard.ServerEndpointExporter;


/**
 * @author liuqi
 * @description 开启websocket支持
 * @date 2019/4/27 16:08
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){

        return new ServerEndpointExporter();
    }
}
