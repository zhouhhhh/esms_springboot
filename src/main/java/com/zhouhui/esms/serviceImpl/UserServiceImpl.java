package com.zhouhui.esms.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.User;
import com.zhouhui.esms.mapper.UserMapper;
import com.zhouhui.esms.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage<User> findUsersAndDepartmentNameByPage(IPage<User> page,
                                                  @Param(Constants.WRAPPER)
                                                          QueryWrapper<User> userQueryWrapper) {
        UserMapper userMapper = getBaseMapper();
        return userMapper.findUsersAndDepartmentNameByPage(page,userQueryWrapper);
    }

    @Override
    public List<User> findUsersAndDepartmentName(QueryWrapper<User> userQueryWrapper) {
        UserMapper userMapper = getBaseMapper();
        return userMapper.findUsersAndDepartmentName(userQueryWrapper);
    }

    @Override
    public boolean login(User user) {
        String userCode = user.getUserCode();
        String userPassword = user.getUserPassword();
        if(StrUtil.isBlank(userCode) || StrUtil.isBlank(userPassword)){
            return false;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code",userCode);
        queryWrapper.eq("user_password",userPassword);
        UserMapper userMapper = getBaseMapper();
        Long aLong = userMapper.selectCount(queryWrapper);
        if (aLong == 1){
            return true;
        }else {
            return false;
        }


    }
}
