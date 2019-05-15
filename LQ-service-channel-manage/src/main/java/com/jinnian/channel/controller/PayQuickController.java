package com.jinnian.channel.controller;

import com.framework.model.channel.entity.PaylogDo;
import com.jinnian.channel.mapper.PaylogDoMapper;
import com.minghao.framework.common.response.ResultBean;
import com.minghao.framework.util.SnowflakeIdWorker;
import com.minghao.framework.util.TestUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program:jinxianianhua
 * @authod liuqi
 * @date 2019/5/15 11:47
 * @Description: 快捷支付
 * @Version 1.0
 **/
@RestController
@RequestMapping("/channel/quick")
public class PayQuickController {

    private static final Logger log= LoggerFactory.getLogger(PayAliController.class);

    @Value("${merid}")
    private String merid;// 分配的商户号
    @Value("${key}")
    private String key;// 商户号对应的密钥
    @Value("${notifyUrl}")
    private String notifyUrl;// 用于接收回调通知的地
    @Value("${noncestr}")
    private String noncestr;//随机参数
    @Value("${returnUrl}")
    private String returnUrl;//支付完成跳转页面
    @Value("${subMerchantName}")
    private String subMerchantName;//二级商户名称

    @Autowired
    private PaylogDoMapper paylogDoMapper;

    /***
     *
     * @param paylogDo 订单信息： 用户id，支付金额，渠道id
     * @return
     */
    /*@RequestMapping(value = "/payLog",method = RequestMethod.POST,produces = "application/json")*/
    @PostMapping(value = "/payLog",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "提交银联快捷支付", notes = "true:提交成功,false:提交失败")
    public ResultBean<String> Pay(@RequestBody PaylogDo paylogDo){
        //第三方接口
        String wxurl="https://alipay.3c-buy.com/api/createQuickOrder";

        Map<String, String> paramMap = new HashMap<String, String>();

        //产生支付单号
        SnowflakeIdWorker idWorker0 = new SnowflakeIdWorker(0, 0);
        String merchantOutOrderNo = idWorker0.nextId()+"";
        //订单时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderTime = sdf.format(new Date());

        paramMap.put("merchantOutOrderNo",merchantOutOrderNo);
        paramMap.put("merid",merid);
        paramMap.put("noncestr",noncestr);
        paramMap.put("notifyUrl", notifyUrl);
        paramMap.put("orderMoney",paylogDo.getPayAmount().toString());
        paramMap.put("orderTime",orderTime);

        //添加订单信息
        PaylogDo insertPayLogDO=new PaylogDo();
        insertPayLogDO.setUserId(paylogDo.getUserId());
        insertPayLogDO.setChannelId(paylogDo.getChannelId());//渠道id
        insertPayLogDO.setPayAmount(paylogDo.getPayAmount()); //金额
        insertPayLogDO.setPayStatus(3);//支付状态：1-成功；2-失败；3-等待支付
        insertPayLogDO.setPayType(3);//支付方式：1-支付宝；2-微信；3-银联
        insertPayLogDO.setGmtCreate(new Date());
        insertPayLogDO.setMerchantOutOrderNo(merchantOutOrderNo); //编号

        paylogDoMapper.insertSelective(insertPayLogDO);

        String StringA= TestUtil.formatUrlMap(paramMap,false, false);//待签名串
        String sign=TestUtil.getMD5(StringA+"&key="+key);//签名

        //对参数按照key=value的格式,参照参数名ASCII码排序,并对value做utf-8的encode编码后得到字符串 param
        String param = TestUtil.formatUrlMap(paramMap, true, false);

        //#二级商户名称 银联页面显示的商户名称，不填则默认，请保证该名称已在一麻袋后台的二级商户名称中添加，并通过白名单审核，否则会报“二级商户名称不合法”
        String url = wxurl + "?" + param + "&sign=" + sign + "&id=" + paylogDo.getUserId()+"&returnUrl="+returnUrl;//+"&subMerchantName="+subMerchantName;

        log.info("url-[{}]",url);

        ResultBean<String> resultBean = new ResultBean<>();
        resultBean.setData(url);
        resultBean.setCode(200);
        resultBean.setSuccess(true);
        resultBean.setMsg("银联快捷支付");
        return resultBean;
    }
}
