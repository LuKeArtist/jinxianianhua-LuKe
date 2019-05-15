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

    //添加
    ChannelDo addAll(ChannelDo channelDo);

    //查询
    ChannelDo findAll(ChannelDo channelDo);

    /**
     * 修改
     * @param channelDo
     */
    int update(ChannelDo channelDo);


    /**
     * 通过用户名查询
     * @param name 用户名
     * @return 管理员用户
     */
    ChannelDo findByName(String name);

}
