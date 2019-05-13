package com.jinnian.channel.controller;

import com.framework.model.channel.entity.ChannelDo;
import com.jinnian.channel.service.UserService;
import com.jinnian.framework.common.response.ResultBean;
import com.jinnian.framework.util.pictureUtils.TokenProccessor;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @authod liuqi
 * @date 2019/5/13 13:10
 */
@RestController
@RequestMapping("/channel")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, origins = "*")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    @ApiOperation(value = "后台用户登录")
    public ResultBean login(@RequestBody ChannelDo channel, HttpSession httpSession) {

        //前端传给我的
        System.out.println(channel.getPassword());
        System.out.println(channel.getUsername());


        ChannelDo us = userService.login(channel);
        //用户名和密码
        if (us == null) {
            return ResultBean.ofError(0, "登陆失败");
        } else {

            //用户 id
            Integer userId = us.getId();
            System.out.println(userId);

            //登陆
            channel.setId(channel.getId());

            //获取用户登录的token
            channel.setToken(TokenProccessor.getInstance().makeToken());
            //添加到数据库
            channel.setLast_login_token(channel.getToken());
            String lastLoginToken = channel.getLast_login_token();
            System.out.println(lastLoginToken);

            //获取当前时间
            channel.setLogin_at(new Date());
            System.out.println(new Date());
            userService.addUser(channel);


            //保存用户id到session
            //用户 id
            httpSession.setAttribute("user_long_id", userId);

            System.out.println("sessionId:" + httpSession.getId());
            System.out.println("user_integer_id用户id为：" + userId);


            return ResultBean.ofSuccess(1, channel.getToken(), "登陆成功");
        }

    }

}
