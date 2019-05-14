package com.jinnian.channel;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * @author liuqi
 * @date 2019.4.13
 */
//启动类
@SpringBootApplication(exclude = {PageHelperAutoConfiguration.class})
//跨域
@EnableTransactionManagement
@EnableScheduling

@ComponentScans(@ComponentScan("com.jinnian.framework"))
// 扫描common、model、api、util下的所有类
@EntityScan("com.jinnian.framework.model") // 扫描实体类
@MapperScan("com.jinnian.channel.mapper")  //扫描接口

public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

        System.out.println("代码开源不易，且珍惜!");
        System.out.println("2019年，祝每一个努力的人都有所收获");


    }

}
