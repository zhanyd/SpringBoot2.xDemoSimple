package com.zhanyd.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zhanyd@sina.com on 2021/10/13
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${swagger.enable:true}")
    private Boolean swaggerFlag;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .enable(swaggerFlag)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhanyd.app.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("XX系统接口文档")
                .description("系统接口文档")
                .version("3.0")
                .build();
    }
}
