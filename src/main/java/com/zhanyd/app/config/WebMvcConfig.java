package com.zhanyd.app.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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
                .excludePathPatterns("/views/**","/assets/**","/user/isUserExist","/user/login","/",
						"/swagger-resources/**","/v3/api-docs","/swagger-ui/**");
    }
    
    /**
   	 * 配置跨域请求
   	 * @return
   	 */
   	@Bean
   	public Filter corsFilter(){
   		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
   		CorsConfiguration config = new CorsConfiguration();
   		//config.addAllowedOrigin("http://localhost:8080");
   		config.addAllowedOrigin("*");
   		config.setAllowCredentials(true);
   		config.addAllowedHeader("*");
   		config.addAllowedMethod("*");
   		source.registerCorsConfiguration("/**", config);
   		return new CorsFilter(source);
   	}
}
