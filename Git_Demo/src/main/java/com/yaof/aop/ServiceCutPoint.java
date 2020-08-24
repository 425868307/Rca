package com.yaof.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ServiceCutPoint {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceCutPoint.class);
	
	@Autowired(required=true)
	private HttpServletRequest request;
	

	@Before("execution(* com.yaof.service.**.*(..))")
	public void before(){
		logger.info("execution(* com.yaof.service.**.*(..))");
	}
	
	/**
     * 2.定义切点
     * 切在什么地方  方法  注解
     * 例如事务 切方法 统一处理简单 切注解 灵活
     */
    @Pointcut("@annotation(com.yaof.aop.MyAnno)")
    public void logPoint(){}
	
    @Before("logPoint()")
    public void cutBefore(JoinPoint joinPoint){
    	logger.info("============logPoint before===========");
    	logger.info(joinPoint.getTarget().toString());
    }
    
    @After(" logPoint()")
    public void logInsert(JoinPoint joinPoint){
    	logger.info("============logPoint @After===========");
    	logger.info(joinPoint.getStaticPart().toString());
    	
    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        //通过该方法获得标注的自定义注解
        MyAnno annotation = method.getAnnotation(MyAnno.class);
        String value = annotation.value();
        logger.info(value);
    }
    
    @Around("logPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    	logger.info("around invoke before, params:"+joinPoint.getArgs());
    	
    	Object result = joinPoint.proceed();
    	logger.info("around invoke after, result:"+result);
		return result;
        
    }
}
