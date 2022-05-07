package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.Role;
import com.zhouhui.esms.entity.RoleMenu;
import com.zhouhui.esms.mapper.RoleMapper;
import com.zhouhui.esms.mapper.RoleMenuMapper;
import com.zhouhui.esms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色;不同的角色有不同的权限 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {

        //先删后增
        roleMenuMapper.deleteRoleMenu(roleId);

        for (Integer id:menuIds){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(id);
            roleMenuMapper.insert(roleMenu);
        }



    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectListByRoleId(roleId);
    }
}
