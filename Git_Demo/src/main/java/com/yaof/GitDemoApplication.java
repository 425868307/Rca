package com.yaof;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * springboot 启动文件
 *
 */
@SpringBootApplication
@ImportResource(locations={"classpath:spring-mybatis.xml"})
public class GitDemoApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(GitDemoApplication.class);
	
	public static void main( String[] args ) {
        SpringApplication.run(GitDemoApplication.class, args);
        logger.info("SpringBoot started!!!");
    }
	
	/**
	 * 打包war包，外部启动的方法
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GitDemoApplication.class);  
	}
}
