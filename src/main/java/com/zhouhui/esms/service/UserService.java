package com.zhouhui.esms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhouhui.esms.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
public interface UserService extends IService<User> {

    IPage<User> findUsersAndDepartmentName(IPage<User> page,
                                           @Param(Constants.WRAPPER) QueryWrapper<User> userQueryWrapper);
}
