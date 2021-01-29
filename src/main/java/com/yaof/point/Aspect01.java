package com.yaof.point;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by yaof on 2020/10/15.
 */
@Aspect
@Component
public class Aspect01 {

    @Pointcut("@annotation(AspAnno)")
    public void logPointCut() {

    }

    /**
     * 切入，方法切入，切入被AspAnno注解的方法
     *
     * @param point
     */
//	@After("execution(* com.yaof.service.*.*(..))")
    @After("logPointCut()")
    public void afterMethod(JoinPoint point) {
        System.out.println("point:" + point.getTarget().toString());
    }
}
