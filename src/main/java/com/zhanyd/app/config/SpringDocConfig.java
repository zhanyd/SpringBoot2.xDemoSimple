package com.zhanyd.app.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanyd@sina.com on 2021/10/13
 */
@Configuration
public class SpringDocConfig {

    @Value("${swagger.enable:true}")
    private Boolean swaggerFlag;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .pathsToMatch("/public/**")
                .build();
    }
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("springshop-admin")
                .pathsToMatch("/admin/**")
                .build();
    }
}
