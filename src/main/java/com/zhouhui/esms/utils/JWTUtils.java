package com.zhouhui.esms.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zhouhui.esms.entity.User;
import com.zhouhui.esms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 *
 * @author zhou
 */
@Component
public class JWTUtils {

    private static UserService staticuserService;

    private static final String secret = "zhouhui1234567";
    private static final int expire = 2;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void setUserService(){
        staticuserService = userService;
    }
    /**
     * 生成token
     */
    public static String createToken(String userId){
        return JWT.create().withAudience(userId) //将userid作为载荷存入token
                .withExpiresAt(DateUtil.offsetHour(new Date(),expire)) //2小时后token过期
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static User getCurrentUser(){
        try
        {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(StrUtil.isNotBlank(token)){
                String Id = JWT.decode(token).getAudience().get(0);
                return staticuserService.getById(Integer.valueOf(Id));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
