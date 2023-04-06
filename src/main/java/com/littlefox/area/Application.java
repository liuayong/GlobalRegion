package com.littlefox.area;

import com.littlefox.area.config.MsgUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"com.hspedu", "com.littlefox.area", "com.mexue.middle.school"})
@MapperScan({"com.littlefox.**.dao",   "com.mexue.middle.school.mapper"}) // , "com.mexue.**.mapper"
@EnableFeignClients
@EnableRetry
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    
    public Application(MessageSource messageSource) {
        MsgUtil.inti(messageSource);
    }
    
    
    @Configuration
    public class MyMvcConfig implements WebMvcConfigurer {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("area/index");
        }
    }

}
