package com.yaof;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ImportResource(value = {"classpath:spring-mybatis.xml"})
public class RecordClearAideApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(RecordClearAideApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(RecordClearAideApplication.class, args);
            logger.info("RecordClearAideApplication start success.");
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            logger.info("RecordClearAideApplication start fail.");
        }
    }

    /**
     * springboot打包成war，使用外部tomcat启动实现方法
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RecordClearAideApplication.class);
    }

}
