package com.yaof.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;

/**
 * 通过实现Filter方法，实现拦截，在Interceptor之前拦截
 */
@Configuration
public class WebComponent2Config {
    @Bean
    public FilterRegistrationBean someFilterRegistration1() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加我们写好的过滤器
        registration.setFilter(new SessionFilter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        return registration;
    }


    private static class SessionFilter implements Filter {
        //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
        Map<String, Object> resp = new HashMap<>();

        {
            resp.put("code", "-1");
            resp.put("msg", "登陆超时,请重新登陆");
        }

        //不需要登录就可以访问的路径(比如:注册登录等)
        String[] includeUrls = new String[]{
                "/register", "/checkAccount", "/login", "/login.jsp", "/swagger_login.jsp", "/swagger-resources/**",
                "/webjars/**", "/v2/**", "/css/**", "/images/**", "/js/**"};


        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                             FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpSession session = request.getSession(false);
            String uri = request.getRequestURI();

            //System.out.println("filter url:"+uri);
            //是否需要过滤
            boolean needFilter = isNeedFilter(request.getContextPath(), uri);


            if (!needFilter) { //不需要过滤直接传给下一个过滤器
                filterChain.doFilter(servletRequest, servletResponse);
            } else { //需要过滤器
                // session中包含user对象,则是登录状态
                if (session != null && session.getAttribute("user") != null) {
                    // System.out.println("user:"+session.getAttribute("user"));
                    filterChain.doFilter(request, response);
                } else {
                    String requestType = request.getHeader("X-Requested-With");
                    //判断是否是ajax请求
                    if (requestType != null && "XMLHttpRequest".equals(requestType)) {
                        response.setContentType("text/html;charset=utf-8");
                        response.getWriter().write(JSON.toJSONString(resp));
                    } else if (uri.startsWith("/rca/swagger-ui")) {
                        //重定向到swagger-ui登录页
                        response.sendRedirect(request.getContextPath() + "/swagger_login.jsp");
                    } else {
                        //重定向到登录页(需要在static文件夹下建立此html文件)
                        response.sendRedirect(request.getContextPath() + "/login.jsp");
                    }
                    return;
                }
            }
        }

        /**
         * @param uri
         * @Author: xxxxx
         * @Description: 是否需要过滤拦截
         * @Date: 2018-03-12 13:20:54
         */
        public boolean isNeedFilter(String pre, String uri) {
            int indexPre = 0;
            int indexPreSuf = 0;

            for (String includeUrl : includeUrls) {
                includeUrl = pre + includeUrl;
                //判断拦截的url是否以**配置，解析**传入的uri以**前的字符串开头即匹配上。
                indexPre = includeUrl.indexOf("/**");
                if (indexPre > 0) {
                    String matchPre = includeUrl.substring(0, indexPre);
                    if (uri.startsWith(matchPre)) {
                        return false;
                    }
                } else {
                    indexPreSuf = includeUrl.indexOf("/*.");
                    if (indexPreSuf > 0) {
                        String[] matchPreSuf = includeUrl.split("/*.");
                        if (matchPreSuf.length == 2 && uri.startsWith(matchPreSuf[0]) &&
                                uri.endsWith(matchPreSuf[1])) {
                            return false;
                        }
                    } else {
                        if (includeUrl.equals(uri)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void destroy() {

        }
    }

}
