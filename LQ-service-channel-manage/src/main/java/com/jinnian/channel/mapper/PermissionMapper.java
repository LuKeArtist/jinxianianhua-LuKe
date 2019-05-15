package com.jinnian.channel.mapper;

import com.framework.model.channel.entity.ChannelDo;
import com.framework.model.channel.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaobb
 * @description
 * @date 2019/5/14 18:22
 */
@Repository
@Mapper
public interface PermissionMapper{
    /**
     * 查询某用户的权限
     * @param user 目标用户
     * @return 权限集合
     */
    List<Permission> queryByUser(ChannelDo user);
}
