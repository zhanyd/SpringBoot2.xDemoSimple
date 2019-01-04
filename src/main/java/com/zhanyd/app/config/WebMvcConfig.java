package com.zhanyd.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.zhanyd.app.common.interceptor.PermissionInterceptor;


/**
 * Created by zhanyd@sina.com on 2019/1/04
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 添加过滤链
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/views/**","/assets/**","/user/isUserExist","/user/login","/");
    }
}
