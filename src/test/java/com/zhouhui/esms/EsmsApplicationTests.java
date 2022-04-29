package com.zhouhui.esms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhouhui.esms.entity.User;
import com.zhouhui.esms.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class EsmsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserMapper userMapper;
    @Test
    void addAdminUserTest(){
        User user = new User();
        user.setUserCode("管理员");
        user.setUserName("admin");
        user.setUserPassword("123456");
        user.setGender("男");
        user.setUserDepartmentId(1);
        Calendar c1 = Calendar.getInstance();
        //月份是从0开始到11
        c1.set(2000,10,3,12,0,0);
        user.setBirthday(c1.getTime());
        user.setPhone("17733638643");
        user.setEmail("760279105@qq.com");
        userMapper.insert(user);
    }

    @Test
    void updateUser(){
        User user = userMapper.selectById(1);
        LocalDateTime localDateTime  =
                LocalDateTime.of(2000,10,3,6,0,0);
        user.setBirthday(Date.from(localDateTime.toInstant(ZoneOffset.of("+8"))));
        userMapper.updateById(user);

    }

}
