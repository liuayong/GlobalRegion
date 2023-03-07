package com.littlefox.area.utils;

import com.littlefox.area.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j

@SpringBootApplication(scanBasePackages = {"com.hspedu", "com.littlefox.area", "com.mexue.middle.school"})
@MapperScan({"com.littlefox.**.dao", "com.mexue.middle.school.mapper"}) // , "com.mexue.**.mapper"
public class SpringUtilTest2 {

    public static void main(String[] args) {
        SpringApplication.run(SpringUtilTest2.class, args);
        ApplicationContext context = SpringUtil.getApplicationContext();
        BookService bookService = context.getBean(BookService.class);
        log.info("bookService={}, classType={}", bookService, bookService.getClass().getName());
        Long saveId = bookService.save();
        System.out.printf("saveId = %s完成\n", saveId);

        BookService bookService2 = SpringUtil.getBean(BookService.class);
        log.info("bookService2={}, classType={}", bookService2, bookService2.getClass().getName());
        System.out.println((bookService == bookService2) + ", " + bookService.equals(bookService2));
    }


}