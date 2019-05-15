package com.jinnian.channel.config;


import com.framework.model.channel.entity.ChannelDo;
import com.framework.model.channel.entity.Permission;
import com.framework.model.channel.entity.Role;
import com.jinnian.channel.service.PermissionService;
import com.jinnian.channel.service.RoleService;
import com.jinnian.channel.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuqi
 * @description shiro授权，认证 realm,shiro需要自己处理异常
 * @date 2019/4/28 9:47
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登陆用户后查询该用户角色和权限并赋值
        ChannelDo user = (ChannelDo) SecurityUtils.getSubject().getPrincipal();
        List<Role> roles = roleService.queryRolesOfUser(user);
        List<Permission> permissions = permissionService.queryByUser(user);
        user.setRoles(roles);
        user.setPermissions(permissions);

        roles.forEach( role ->info.addRole(role.getDisplayName()));
        permissions.forEach(permission -> info.addStringPermission(permission.getName()));
        return info;
    }

    /**
     * 登陆
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        ChannelDo user = userService.findByName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            String password = user.getPassword();
            SimpleAuthenticationInfo simpleAuthenticationInfo =
                    //shiro自动检验密码
                    new SimpleAuthenticationInfo(name, password, getName());
            return simpleAuthenticationInfo;
        }
    }

}
