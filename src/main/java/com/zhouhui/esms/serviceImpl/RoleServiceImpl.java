package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.Role;
import com.zhouhui.esms.mapper.RoleMapper;
import com.zhouhui.esms.service.RoleService;
import org.springframework.stereotype.Service;

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

}
