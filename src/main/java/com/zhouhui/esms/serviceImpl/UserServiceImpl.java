package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.User;
import com.zhouhui.esms.mapper.UserMapper;
import com.zhouhui.esms.service.UserService;
import com.zhouhui.esms.utils.JWTUtils;
import com.zhouhui.esms.utils.exceptionhandler.BizException;
import com.zhouhui.esms.utils.exceptionhandler.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage<User> findUsersAndDepartmentNameByPage(IPage<User> page,
                                                        @Param(Constants.WRAPPER)
                                                                QueryWrapper<User> userQueryWrapper)
    {
        UserMapper userMapper = getBaseMapper();
        return userMapper.findUsersAndDepartmentNameByPage(page, userQueryWrapper);
    }

    @Override
    public List<User> findUsersAndDepartmentName(QueryWrapper<User> userQueryWrapper) {
        UserMapper userMapper = getBaseMapper();
        return userMapper.findUsersAndDepartmentName(userQueryWrapper);
    }

    @Override
    public User login(User user) {
        String userCode = user.getUserCode();
        String userPassword = user.getUserPassword();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code", userCode);
        queryWrapper.eq("user_password", userPassword);
        UserMapper userMapper = getBaseMapper();
        try
        {
            User selectOne = userMapper.selectOne(queryWrapper);
            if(selectOne != null){
                String token = JWTUtils.createToken(selectOne.getUserId().toString(),selectOne.getUserPassword());
                selectOne.setToken(token);
            }
            return selectOne;
        }
        catch (Exception e)
        {
            log.error("发生业务逻辑异常！原因是：" + e);
            throw new BizException(ExceptionEnum.SERVICE_ERROR);
        }

    }
}
