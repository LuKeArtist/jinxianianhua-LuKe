package com.jinnian.channel.service.Impl;

import com.framework.model.channel.entity.ChannelDo;
import com.framework.model.channel.entity.Role;
import com.jinnian.channel.mapper.RoleMapper;
import com.jinnian.channel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuqi
 * @description
 * @date 2019/5/14 18:03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryRolesOfUser(ChannelDo user) {

        return roleMapper.queryRolesOfUser(user);
    }
}
