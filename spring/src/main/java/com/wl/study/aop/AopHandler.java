package com.wl.study.aop;

import com.mysql.jdbc.NonRegisteringDriver;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

/**
 * @Author:weilu
 * @Date: 2019/1/20 19:46
 */
@Service
@Aspect
public class AopHandler {

    private static final String executionString = "execution(* com.wl.study.aop.AopServiceImpl.test())";

    @Around(executionString)
    public void handle(ProceedingJoinPoint point)throws Throwable{
        System.out.println(1);
        point.proceed();
        System.out.println(2);
    }
}
