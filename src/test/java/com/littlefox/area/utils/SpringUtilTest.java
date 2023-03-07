package com.littlefox.area.utils;

import com.littlefox.area.Application;
import com.littlefox.area.service.BookService;
import com.littlefox.area.service.imps.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringUtilTest {
    @Resource
    private BookService bookService2;
    @Resource
    private BookServiceImpl bookService3;

    public static void main(String[] args) {
        SpringApplication.run(SpringUtilTest.class, args);
        ApplicationContext context = SpringUtil.getApplicationContext();
        BookService bookService = context.getBean(BookService.class);
        log.info("bookService={}, classType={}", bookService, bookService.getClass().getName());
        Long saveId = bookService.save();
        System.out.printf("saveId = %s完成\n", saveId);
    }

    @Test
    public void getBean() {
        ApplicationContext context = SpringUtil.getApplicationContext();
        BookService bookService = context.getBean(BookService.class);
        log.info("bookService={}, classType={}", bookService, bookService.getClass().getName());
        log.info("bookService2={}, classType={}", bookService2, bookService2.getClass().getName());
        log.info("bookService3={}, classType={}", bookService3, bookService3.getClass().getName());
        System.out.println((bookService == bookService2) + ", " + bookService.equals(bookService2));
        System.out.println((bookService3 == bookService2) + ", " + bookService3.equals(bookService2));
        System.out.printf("getBean: saveId = %s完成\n", bookService.save());
    }


}