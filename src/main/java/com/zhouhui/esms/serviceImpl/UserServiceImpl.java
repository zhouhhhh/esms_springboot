package com.zhouhui.esms.serviceImpl;

import com.zhouhui.esms.entity.User;
import com.zhouhui.esms.mapper.UserMapper;
import com.zhouhui.esms.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
