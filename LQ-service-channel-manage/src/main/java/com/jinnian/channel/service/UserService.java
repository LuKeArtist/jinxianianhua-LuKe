package com.jinnian.channel.service;


import com.framework.model.channel.entity.ChannelDo;
import com.framework.model.channel.entity.CodeDo;


/**
 * @authod liuqi
 * @date 2019/5/13 11:32
 */
public interface UserService {

    //登陆
    ChannelDo login(ChannelDo channelDo);


    //修改
    ChannelDo addUpdate(ChannelDo channelDo);

    //添加
    ChannelDo addAll(ChannelDo channelDo);

    //查询
    ChannelDo findAll(ChannelDo channelDo);

}
