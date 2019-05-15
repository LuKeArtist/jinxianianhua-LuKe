package com.jinnian.channel.mapper;

import com.framework.model.channel.entity.PaylogDo;
import com.minghao.framework.model.channel.query.PayLogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaylogDoMapper {


    //跟住id查询支付状态
    PaylogDo selectByPrimaryKey(Long key);

    //保存
    int insertSelective(PaylogDo record);


    List<PaylogDo> query(PayLogQuery query);

    String Pay(String id, String orderMoney);

    //修改订单状态
    int updateOrderStatus(PaylogDo record);


}