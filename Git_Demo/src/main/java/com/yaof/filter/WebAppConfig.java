package com.yaof.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
拦截器配置类
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    // 多个拦截器组成一个拦截器链
    // addPathPatterns 用于添加拦截规则
    // excludePathPatterns 用户排除拦截

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())//添加拦截器
                .addPathPatterns("/**") //拦截所有请求
                .excludePathPatterns("/test/test", "/Doctor/**", "/SMS/**");//对应的不拦截的请求
    }
    
    public class LoginInterceptor implements HandlerInterceptor {

        /**
         * 进入controller层之前拦截请求
         *
         * @param httpServletRequest
         * @param httpServletResponse
         * @param o
         * @return
         * @throws Exception
         */
        @Override
        public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        	log.info("---------------开始进入地址拦截器-------------------");
        	return true;
        }
        //访问controller之后 访问视图之前被调用
        @Override
        public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
            log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
        }
        //访问视图之后被调用
        @Override
        public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
            log.info("---------------视图渲染之后的操作-------------------------0");
        }

    }
}
