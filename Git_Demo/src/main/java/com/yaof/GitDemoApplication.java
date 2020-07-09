package com.yaof;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * springboot 启动文件
 *
 */
@SpringBootApplication
public class GitDemoApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(GitDemoApplication.class);
	
	public static void main( String[] args ) {
        SpringApplication.run(GitDemoApplication.class, args);
        logger.info("SpringBoot started!!!");
    }
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GitDemoApplication.class);  
	}
}
