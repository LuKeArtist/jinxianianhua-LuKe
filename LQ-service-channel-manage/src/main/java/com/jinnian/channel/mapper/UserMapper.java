package com.jinnian.channel.mapper;

import com.framework.model.channel.entity.ChannelDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @authod liuqi
 * @date 2019/5/13 11:53
 */

@Repository
@Mapper
public interface UserMapper {



    //登陆
    ChannelDo login(ChannelDo channelDo);

    //添加
    ChannelDo addUser(ChannelDo channelDo);











}
