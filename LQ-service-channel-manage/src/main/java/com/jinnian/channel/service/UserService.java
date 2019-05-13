package com.jinnian.channel.service;


import com.framework.model.channel.entity.ChannelDo;


/**
 * @authod liuqi
 * @date 2019/5/13 11:32
 */
public interface UserService {

    //登陆
    ChannelDo login(ChannelDo channelDo);

    //修改
    ChannelDo addUpdate(ChannelDo channelDo);
}
