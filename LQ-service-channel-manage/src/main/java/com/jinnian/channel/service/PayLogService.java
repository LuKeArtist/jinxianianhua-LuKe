package com.jinnian.channel.service;

import com.framework.model.channel.entity.PaylogDo;

/**
 * Description: jinnian
 *  支付宝
 * @author liuqi
 * @date 2019/5/15 09:20
 */
public interface PayLogService {


    /**
     * 支付
     * @param
     * @param paylogDo
     * @return
     */
    String Pay(PaylogDo paylogDo);


    /**
     *  //跟住用户id查询支付状态
     * @param tradeNo
     * @return
     */
    PaylogDo findById(Long tradeNo);

}
