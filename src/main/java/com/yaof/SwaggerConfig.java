package com.yaof;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Description: <br>
     *
     * @return <br>
     * @author luoluocaihong<br>
     * @taskId <br>
     */
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot swagger restful request test")
                .description("测试maven项目的请求功能是否正常。")
                .license("")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .build();
    }

    /**
     * Description: <br>
     *
     * @return <br>
     * @author luoluocaihong<br>
     * @taskId <br>
     */
    @Bean
    public Docket customImplementation() {
        System.out.println("-------------------swagger配置--------------------------");    //加载时自动注入方法，启动时打印日志
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yaof"))
                .paths(PathSelectors.any())
                .build();
    }
}
