package com.zhouhui.esms.mapper;

import com.zhouhui.esms.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色;不同的角色有不同的权限 Mapper 接口
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
