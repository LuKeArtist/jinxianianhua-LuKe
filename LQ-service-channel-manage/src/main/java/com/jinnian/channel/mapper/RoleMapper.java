package com.jinnian.channel.mapper;

import com.framework.model.channel.entity.ChannelDo;
import com.framework.model.channel.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaobb
 * @description 角色mapper接口
 * @date 2019/5/14 18:04
 */
@Repository
@Mapper
public interface RoleMapper {
    /**
     *查询用户的角色
     * @param user 当前用户
     * @return 角色集合
     */
    List<Role> queryRolesOfUser(ChannelDo user);


}
