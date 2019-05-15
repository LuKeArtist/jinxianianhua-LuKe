package com.framework.model.channel.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付
 * @authod liuqi
 * @date 2019/5/15 9:52
 */

@Data
public class PaylogDo {

    private Long tradeNo;

    private Integer userId;

    private Integer channelId;

    private Date payTime;

    private BigDecimal payAmount;

    private Integer payStatus;

    private Integer payType;

    private Date gmtCreate;

    private String merchantOutOrderNo;


    @Override
    public String toString() {
        return "PaylogDo{" +
                "tradeNo=" + tradeNo +
                ", userId=" + userId +
                ", channelId='" + channelId + '\'' +
                ", payTime=" + payTime +
                ", payAmount=" + payAmount +
                ", payStatus=" + payStatus +
                ", payType=" + payType +
                ", gmtCreate=" + gmtCreate +
                ", merchantOutOrderNo='" + merchantOutOrderNo + '\'' +
                '}';
    }
}
