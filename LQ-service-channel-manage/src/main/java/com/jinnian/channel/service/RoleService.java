package com.jinnian.channel.service;

import com.framework.model.channel.entity.ChannelDo;
import com.framework.model.channel.entity.Role;

import java.util.List;

/**
 * @author liuqi
 * @description
 * @date 2019/5/15 18:02
 */
public interface RoleService {
    /**
     * 查询用户的角色集
     * @param user 用户
     * @return 角色集合
     */
    List<Role> queryRolesOfUser(ChannelDo user);
}
