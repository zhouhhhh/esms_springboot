package com.zhouhui.esms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhouhui.esms.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色;不同的角色有不同的权限 服务类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
public interface RoleService extends IService<Role> {


    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);
}
