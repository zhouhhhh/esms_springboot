package com.zhouhui.esms.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zhouhui.esms.entity.User;
import com.zhouhui.esms.service.UserService;
import com.zhouhui.esms.utils.exceptionhandler.BizException;
import com.zhouhui.esms.utils.exceptionhandler.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt拦截器
 * @author zhou
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        if(StrUtil.isBlank(token)){
            throw new BizException(ExceptionEnum.NOT_TOKEN);
        }
        String userId;
        try
        {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e){
            throw new BizException(ExceptionEnum.TOKEN_VERIFY_ERROR);
        }
        User user = userService.getById(userId);
        if(user == null){
            throw new BizException(ExceptionEnum.NOT_EXIST_USER);
        }
        JWTVerifier build = JWT.require(Algorithm.HMAC256("zhouhui1234567")).build();
        try
        {
            build.verify(token);
        }catch (JWTVerificationException e){
            throw new BizException(ExceptionEnum.TOKEN_VERIFY_ERROR);
        }
        return true;
    }
}
