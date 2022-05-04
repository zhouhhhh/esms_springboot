package com.zhouhui.esms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zhouhui.esms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    /**
     * 此方法是分页多表查询用户和用户的部门名称
     * @param page
     * @param userQueryWrapper
     * @return
     */
    IPage<User> findUsersAndDepartmentNameByPage(IPage<User> page,
                                           @Param(Constants.WRAPPER) QueryWrapper<User> userQueryWrapper);

    List<User> findUsersAndDepartmentName(@Param(Constants.WRAPPER) QueryWrapper<User> userQueryWrapper);
}
