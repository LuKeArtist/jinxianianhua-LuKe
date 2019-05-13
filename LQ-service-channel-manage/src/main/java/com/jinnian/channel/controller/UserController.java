package com.jinnian.channel.controller;

import com.framework.model.channel.entity.ChannelDo;
import com.framework.model.channel.entity.CodeDo;
import com.jinnian.channel.service.CodeService;
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

    @Autowired
    private CodeService codeService;


    @PostMapping(value = "/register")
    @ApiOperation(value = "用户注册")
    public ResultBean register(String password, String phone, String code) {

        //接收前端传过来的参数
        System.out.println(phone);
        System.out.println(code);
        System.out.println(password);

       //获取手机号
        ChannelDo cd=new ChannelDo();
        cd.setPhone(phone);
        cd.setPassword(password);

        //调用方法查询数据库的验证码
        CodeDo codeDo = new CodeDo();
        codeDo.setCode(code);
        CodeDo usercode = codeService.findAll(codeDo);

        //调用登陆的方法
        //ChannelDo us = userService.login(cd);
        //查询
        ChannelDo us = userService.findAll(cd);
        if(!usercode.getCode().equals(code)) {
            return ResultBean.ofError(0, "验证码不匹配，注册失败");
        }
        if (us != null) {
            System.out.println(us+"4444444444444444444444");
            return ResultBean.ofError(0, "手机号已存在,注册失败");
        } else {


            //添加到数据库
            //同步手机号和用户名，保持一致
            cd.setUsername(cd.getPhone());
            //用户表
            cd.setPassword(password);
            cd.setPhone(phone);
            System.out.println(cd+"111111111111111111111111111111111111111");
            userService.addAll(cd);

            //验证码表
            codeDo.setPhone(phone);
            codeDo.setCode(code);
            System.out.println(codeDo+"222222222222222222222222222222222222222");
            codeService.addAll(codeDo);
        }

        //返回结果信息
        return ResultBean.ofSuccess(1,"注册成功");

    }




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

           // userService.addUpdate(channel);


            //保存用户id到session
            httpSession.setAttribute("user_long_id", userId);

            System.out.println("sessionId:" + httpSession.getId());
            System.out.println("user_integer_id用户id为：" + userId);


            return ResultBean.ofSuccess(1, channel.getToken(), "登陆成功");
        }

    }

}
