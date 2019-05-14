package com.jinnian.channel.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinnian.channel.service.UserService;
import com.minghao.framework.common.response.ResultBean;
import com.minghao.framework.model.channel.entity.UserDO;
import com.minghao.framework.util.JudgeUserUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @program:jinxianianhua
 * @ClassName:WeChatAuthorizedController
 * @author:liuqi
 * @create:2019-05-14 11:11
 * @Description: 微信公众号授权:当一个用户点击一个公众号的一个特定链接，判断用户是否关注公众号，若没有关注就跳转到关注界面，已关注就跳转到指定界面
 * @Version 1.0
 **/
@RestController
@RequestMapping("/channel/wechat")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, origins = "*")
public class WeChatAuthorizedController {


    @Autowired
    private UserService userService;

    @Value("${wxappid}")
    private String wxappid;// 公众号的唯一标识
    @Value("${redirect_uri}")
    private String redirect_uri;// 授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
    @Value("${redirect_uri2}")
    private String redirect_uri2;
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

    /** 
    * @Description: 微信公众号授权发送
    * @Param: [request, response]
    * @return: [request, response]
    * @Author: liuqi
    * @Date: 19-5-14
    */ 
    @RequestMapping("/doPost")
    @ApiOperation(value = "微信公众号授权发送" ,notes ="true:提交成功,false:提交失败")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //回调地址
        String redirect_uriStr= URLEncoder.encode(redirect_uri,"UTF-8");

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
    }
    
    /** 
    * @Description: 获取code，再通过处理数据得到openid 判断是否关注
    * @Param: [request, response]
    * @return: [request, response]
    * @Author: liuqi
    * @Date: 19-5-14
    */
    @RequestMapping("/doGet")
    @ApiOperation(value = "微信公众号授权验证" ,notes ="true:提交成功,false:提交失败")
    public void doGet(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws ServletException,IOException{

        //System.out.println(request.getUserPrincipal().getName()+"================");
        //获取登录用户id
        System.out.println("微信sessionId"+httpSession.getId());
        Long user_long_id= (Long) httpSession.getAttribute("user_long_id");
        log.info("登录用户id[{}]",user_long_id);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //获取code
        String code=request.getParameter("code");
        log.info("[code为]：[{}]",code);

        //处理数据
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("appid",wxappid);
        params.put("secret",secret);
        params.put("code",code);
        params.put("grant_type",grant_type);

        try {
            //微信开放接口 获取授权access_token 微信这里贼几把坑，两个access_token不一样 他娘的 post
            String wxUrl="https://api.weixin.qq.com/sns/oauth2/access_token";

            //通过code换取网页授权access_token 这里通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同
            String result= HttpUtil.post(wxUrl,params);
            JSONObject jsonObject= JSONUtil.parseObj(result);
            String access_token = jsonObject.get("access_token").toString();
            log.info("[授权access_token为]：[{}]",access_token);

            //微信开放接口 获取基础access_token 微信这里贼几把坑，两个access_token不一样 他娘的 get
            String wxUrl2="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxappid + "&secret=" + secret;

            //获取基础access_token
            String result2= HttpUtil.get(wxUrl2);
            JSONObject jsonObject2= JSONUtil.parseObj(result2);
            String access_token2 = jsonObject2.get("access_token").toString();
            log.info("[基础access_token]：[{}]",access_token2);


        //获取openid
        String openid = jsonObject.get("openid").toString();
        log.info("[openid为]：[{}]",openid);

        //判断用户是否关注 我这里只封装了subscribe字段，如需获取其他值，可以自己扩展即可
        ResultBean<String> resultBean = new ResultBean<>();
        if (JudgeUserUtil.judgeIsFollow(access_token2,openid)){
            //保存用户id到session
            httpSession.setAttribute("rzStatus","rzok");

            System.out.println("sessionId"+httpSession.getId());
            //认证成功
            if(StrUtil.isEmptyIfStr(user_long_id)){
                //用户未登录，认证成功，跳转到登录界面
                //response.sendRedirect("http://www.wa18q.cn:18001/mhyj/dr.html".toString());
                response.sendRedirect("http://aodeng.utools.club/mhyj/dr.html".toString());
            }else{
                //用户已经登录，并且认证成功,修改数据库用户认证状态为1
                UserDO userDO=new UserDO();
                userDO.setId(user_long_id);
                userDO.setWxStatus(1);
                int status = userService.update(userDO);
                if(status>0){
                    log.info("修改数据库用户成功：[{}],认证状态为1",user_long_id);
                }else{
                    log.info("修改数据库用户失败：[{}]",user_long_id);
                }
                //跳转到认证成功界面（认证界面）
                log.info("[微信认证成功，跳转路径]：[{}]","http://www.wa18q.cn:18001/mhyj/rz.html");
                //response.sendRedirect("http://www.wa18q.cn:18001/mhyj/rz.html".toString());
                response.sendRedirect("http://aodeng.utools.club/mhyj/rz.html".toString());
            }
        }else {
            //认证失败
            if(!StrUtil.isEmptyIfStr(user_long_id)){
                //有用户id，认证失败，修改数据库微信认证状态为0（未认证，避免用户取消关注）
                UserDO userDO=new UserDO();
                userDO.setId(user_long_id);
                userDO.setWxStatus(0);
                int status = userService.update(userDO);
                if(status>0){
                    log.info("修改数据库用户成功：[{}],认证状态为0",user_long_id);
                }else{
                    log.info("修改数据库用户失败：[{}]",user_long_id);
                }
            }
            //跳转到失败界面（登录界面）
            log.info("[微信认证失败，跳转路径]：[{}]","http://www.wa18q.cn:18001/mhyj/rz.html");
            //response.sendRedirect("http://www.wa18q.cn:18001/mhyj/dr.html".toString());
            response.sendRedirect("http://aodeng.utools.club/mhyj/dr.html".toString());
        }
        }catch (Exception e){
            log.error("微信授权回调抛的异常"+e.getMessage());
        }
    }
    /**
     * @Description: 获取code，再通过处理数据得到openid
     * @Param: [request, response]
     * @return: [request, response]
     * @Author: liuqi
     * @Date: 19-5-14
     */
    @RequestMapping("/openid")
    @ApiOperation(value = "微信公众号授权验证" ,notes ="true:提交成功,false:提交失败")
    public ResultBean openid(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) throws ServletException,IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //获取code
        String code = request.getParameter("code");
        log.info("[code为]：[{}]", code);

        //处理数据
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appid", wxappid);
        params.put("secret", secret);
        params.put("code", code);
        params.put("grant_type", grant_type);

        //微信开放接口 获取授权access_token
        String wxUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";

        //通过code换取网页授权access_token 这里通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同
        String result = HttpUtil.post(wxUrl, params);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String access_token = jsonObject.get("access_token").toString();
        log.info("[授权access_token为]：[{}]", access_token);

        //微信开放接口 获取基础access_token
        String wxUrl2 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxappid + "&secret=" + secret;

        //获取基础access_token
        String result2 = HttpUtil.get(wxUrl2);
        JSONObject jsonObject2 = JSONUtil.parseObj(result2);
        String access_token2 = jsonObject2.get("access_token").toString();
        log.info("[基础access_token]：[{}]", access_token2);


        //获取openid
        String openid = jsonObject.get("openid").toString();
        log.info("[openid为]：[{}]", openid);
        return ResultBean.ofSuccess(openid,"获取openid成功");
    }

    /**
     * @Description: 获取openUrl  获取授权url
     * @Param: [request, response]
     * @return: [request, response]
     * @Author: liuqi
     * @Date: 19-5-14
     */
    @RequestMapping("/openUrl")
    @ApiOperation(value = "微信公众号授权发送" ,notes ="true:提交成功,false:提交失败")
    public ResultBean openUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //回调地址
        String redirect_uriStr= URLEncoder.encode(redirect_uri2,"UTF-8");

        //拼接参数
        StringBuilder openUrl=new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?"
                +"&appid="+wxappid
                +"&redirect_uri="+redirect_uriStr
                +"&response_type="+response_type
                +"&scope="+scope
                +"&state="+state
                +"&wechat_redirect");

        log.info("获取授权url,[{}]",openUrl);
        //请不要使用get请求，单纯的跳转即可
        return ResultBean.ofSuccess(openUrl,"获取授权url成功");
    }
}