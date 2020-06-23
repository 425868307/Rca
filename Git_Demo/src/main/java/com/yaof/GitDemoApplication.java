package com.yaof;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class GitDemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(GitDemoApplication.class);
	
	public static void main( String[] args ) {
        SpringApplication.run(GitDemoApplication.class, args);
        logger.info("SpringBoot started!!!");
    }
}
