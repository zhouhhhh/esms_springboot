package com.zhouhui.esms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhouhui.esms.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
public interface UserService extends IService<User> {

    IPage<User> findUsersAndDepartmentNameByPage(IPage<User> page,
                                           @Param(Constants.WRAPPER) QueryWrapper<User> userQueryWrapper);
    List<User> findUsersAndDepartmentName(@Param(Constants.WRAPPER) QueryWrapper<User> userQueryWrapper);

    boolean login(User user);

}
