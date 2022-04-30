package com.zhouhui.esms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zhouhui.esms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @param page
     * @return
     */
    IPage<User> findUsersAndDepartmentName(IPage<User> page,
                                           @Param(Constants.WRAPPER) QueryWrapper<User> userQueryWrapper);
}
