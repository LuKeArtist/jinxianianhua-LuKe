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

    //修改
    ChannelDo addUpdate(ChannelDo channelDo);

    //添加
    ChannelDo addAll(ChannelDo channelDo);

    //查询
    ChannelDo findAll(ChannelDo channelDo);

    //修改
    int updateByPrimaryKey(ChannelDo record);

}
