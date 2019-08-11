package com.aaron.springbootDemo.web.config;

import com.aaron.springbootDemo.web.interceptor.RequestApiInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private RequestApiInterceptor requestApiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(requestApiInterceptor).addPathPatterns("/**").excludePathPatterns("/api/user/**");
        registry.addInterceptor(requestApiInterceptor).addPathPatterns("/api/user/info");
    }
}
