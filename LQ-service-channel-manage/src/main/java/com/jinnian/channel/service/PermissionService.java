package com.jinnian.channel.service;

import com.framework.model.channel.entity.ChannelDo;
import com.framework.model.channel.entity.Permission;

import java.util.List;

/**
 * @author xiaobb
 * @description 权限接口
 * @date 2019/5/14 18:20
 */
public interface PermissionService {
    /**
     * 通过某用户的权限
     * @param user 用户
     * @return 权限集合
     */
    List<Permission> queryByUser(ChannelDo user);
}
