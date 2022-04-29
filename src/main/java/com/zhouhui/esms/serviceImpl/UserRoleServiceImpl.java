package com.zhouhui.esms.serviceImpl;

import com.zhouhui.esms.entity.UserRole;
import com.zhouhui.esms.mapper.UserRoleMapper;
import com.zhouhui.esms.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色;用户与角色的中间表 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
