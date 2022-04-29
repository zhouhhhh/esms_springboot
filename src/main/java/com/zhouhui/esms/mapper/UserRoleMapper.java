package com.zhouhui.esms.mapper;

import com.zhouhui.esms.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色;用户与角色的中间表 Mapper 接口
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
