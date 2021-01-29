package com.yaof.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yaof.pojo.User;

/**
 * 通过实现spring的HandlerInterceptor接口，实现拦截
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebAppConfig.class);

    /**
     * 配置静态资源
     */
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/rca/css/**").addResourceLocations("classpath:/rca/css/");
//        registry.addResourceHandler("/rca/images/**").addResourceLocations("classpath:/rca/images/");
//        registry.addResourceHandler("/rca/js/**").addResourceLocations("classpath:/rca/js/");
//        super.addResourceHandlers(registry);
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new InterceptorConfig())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")    //登陆请求
                .excludePathPatterns("/register")    //注册请求
                .excludePathPatterns("/checkAccount")    //用户名校验是否可用
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**");    //拦截器拦截swagger的拦截解除
        super.addInterceptors(registry);
    }

    public class InterceptorConfig implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            logger.info(request.getRequestURI() + "============================================");
            logger.info("拦截账号：" + request.getHeader("test"));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (ObjectUtils.isEmpty(user)) {
                return false;
            }
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                               ModelAndView modelAndView) throws Exception {

        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
                throws Exception {

        }
    }
}
