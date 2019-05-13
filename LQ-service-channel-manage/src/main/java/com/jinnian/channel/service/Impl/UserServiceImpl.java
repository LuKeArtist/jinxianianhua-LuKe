package com.jinnian.channel.service.Impl;

import com.framework.model.channel.entity.ChannelDo;
import com.framework.model.channel.entity.CodeDo;
import com.jinnian.channel.mapper.CodeMapper;
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
     * 修改
     * @param channelDo
     * @return
     */
    @Override
    public ChannelDo addUpdate(ChannelDo channelDo) {

        return userMapper.addUpdate(channelDo);
    }

    /**
     * 添加
     * @param channelDo
     * @return
     */
    @Override
    public ChannelDo addAll(ChannelDo channelDo) {

        return userMapper.addAll(channelDo);
    }

    @Override
    public ChannelDo findAll(ChannelDo channelDo) {

        return userMapper.findAll(channelDo);
    }

}
