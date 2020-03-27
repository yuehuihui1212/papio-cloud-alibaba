package com.papio.common.aspect;

import com.papio.common.enums.ExceptionEnum;
import com.papio.common.exception.PapioException;
import com.papio.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author papio
 * @description 校验登录AOP切面
 * @date 2020/3/27
 * @since
 **/
@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableConfigurationProperties(JwtOperator.class)
public class CheckLoginAspect {
    private final JwtOperator jwtOperator;

    @Around("@annotation(com.papio.common.annotation.CheckLogin)")
    public Object checkLogin(ProceedingJoinPoint joinPoint) {
        //从header中获取token
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes attributes= (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader("X-Token");
            Boolean isValid = jwtOperator.validateToken(token);
            if (!isValid) {
                throw new PapioException(ExceptionEnum.TOKEN_NOT_VALID);
            }
            Claims claims = jwtOperator.getClaimsFromToken(token);
            request.setAttribute("id",claims.get("id"));
            request.setAttribute("wxNickName",claims.get("wxNickName"));
            request.setAttribute("role",claims.get("role"));
           return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new PapioException(ExceptionEnum.TOKEN_NOT_VALID);
        }
    }
}
