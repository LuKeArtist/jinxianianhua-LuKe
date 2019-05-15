package com.jinnian.channel.service.Impl;


import com.framework.model.channel.entity.ChannelDo;
import com.framework.model.channel.entity.Permission;
import com.jinnian.channel.mapper.PermissionMapper;
import com.jinnian.channel.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author liuqi
 * @description 权限接口实现
 * @date 2019/5/15 18:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> queryByUser(ChannelDo user) {

        return permissionMapper.queryByUser(user);
    }
}
