package com.jinnian.channel.controller;

import com.framework.model.channel.entity.PaylogDo;
import com.jinnian.channel.service.PayLogService;
import com.minghao.framework.common.response.ResultBean;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program:jinxianianhua
 * @authod liuqi
 * @date 2019/5/15 9:47
 * 支付宝支付
 */

@RequestMapping("/channel")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, origins = "*")
public class PayAliController {

    private static final Logger log= LoggerFactory.getLogger(PayAliController.class);

    @Autowired
    private PayLogService payLogService;

    /***
     * 支付宝订单支付
     * @param paylogDo  payAmount channelId 订单信息：用户id，支付金额，渠道id
     * @return 返回url和状态
     */
    @PostMapping(value = "/payLog",consumes = "application/json")
    @ApiOperation(value = "提交支付宝支付", notes = "true:提交成功,false:提交失败")
    public ResultBean<String> Pay(@RequestBody PaylogDo paylogDo){

        String url = payLogService.Pay(paylogDo);

        log.info("url-[{}]",url);

        ResultBean<String> resultBean = new ResultBean<>();
        resultBean.setData(url);
        resultBean.setCode(200);
        resultBean.setSuccess(true);
        resultBean.setMsg("支付宝支付");
        return resultBean;
    }

    /**
     * 根据ID去查询支付状态
     */
    @GetMapping("/findOne")
    @ApiOperation(value = "跟住ID去查询支付状态")
    public ResultBean findById(Long tradeNo){

        return ResultBean.ofSuccess(payLogService.findById(tradeNo));
    }

}
