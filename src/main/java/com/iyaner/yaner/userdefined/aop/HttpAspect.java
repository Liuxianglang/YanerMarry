package com.iyaner.yaner.userdefined.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger=LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.iyaner.yaner.controller.*.*(..))")
    public void cut1(){}

    @Before("cut1()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={},ip={}",request.getRequestURL(),request.getRemoteAddr());
        logger.info("class={},method={},args={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @Around("cut1()")
    public Object knowTime(ProceedingJoinPoint joinPoint){
        Object object=null;
        Long b=new Date().getTime();
        try {
            object=joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            Long e=new Date().getTime();
            logger.info("class={},method={},执行时长为:{}ms",joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),(e-b)/1000);
        }
        return object;
    }

}
