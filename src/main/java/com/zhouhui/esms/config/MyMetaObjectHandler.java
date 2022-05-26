package com.zhouhui.esms.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zhouhui.esms.entity.User;
import com.zhouhui.esms.utils.JWTUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 此为创建时间与更新时间在插入更新操作时自动赋值
 * @author zhou
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createdTime",new Date(),metaObject);
        User currentUser = JWTUtils.getCurrentUser();
        if (currentUser != null){
            setFieldValByName("createdBy",currentUser.getUserId(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedTime",new Date(),metaObject);
        User currentUser = JWTUtils.getCurrentUser();
        if (currentUser != null){
            setFieldValByName("updatedBy",currentUser.getUserId(),metaObject);
        }
    }
}
