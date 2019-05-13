package com.jinnian.channel.service.Impl;

import com.framework.model.channel.entity.ChannelDo;
import com.jinnian.channel.mapper.UserMapper;
import com.jinnian.channel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @authod liuqi
 * @date 2019/5/13 11:33
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 登陆
     * @param channelDo
     * @return
     */
    @Override
    public ChannelDo login(ChannelDo channelDo) {
        return userMapper.login(channelDo);
    }

    /**
     * 添加
     * @param channelDo
     * @return
     */
    @Override
    public ChannelDo addUser(ChannelDo channelDo) {
        return userMapper.addUser(channelDo);
    }


}
