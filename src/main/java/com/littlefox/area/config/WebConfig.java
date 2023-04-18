package com.littlefox.area.config;

import com.hspedu.json.jackson.DateFormatInterceptor;
import com.littlefox.area.interceptor.ApiInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Configuration
public class WebConfig implements WebMvcConfigurer {


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiInterceptor())
                .addPathPatterns("/**");

        registry.addInterceptor(dateFormatInterceptor()).addPathPatterns("/**");

    }

    @Bean
    public HandlerInterceptor dateFormatInterceptor() {
        return new DateFormatInterceptor();
    }
}
