package com.jinnian.channel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: liuqi-cloud
 * 测试类
 *
 * @author liuqi
 * @date 2019/4/13 15:15
 */
@Api(description = "Liu")
@RestController
@RequestMapping(value = "/hello")
public class TestController {

    @ApiOperation(value = "hello", notes = "hello")
    @RequestMapping(value = "/hello")
    public String index() {
        System.out.println("output:>>>>>" + "进入controller");
        return "Hi,欢迎进入我的世界，我是liuqi";
    }
}
