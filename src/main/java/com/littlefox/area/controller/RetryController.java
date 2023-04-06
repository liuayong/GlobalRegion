package com.littlefox.area.controller;

import com.littlefox.area.service.RetryableCustomerClient;
import com.littlefox.area.service.imps.RetryableCustomerClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/retry")
public class RetryController {
    @Resource
    private RetryableCustomerClientImpl retryableCustomerClient;

    /**
     * http://localhost:8080/retry/hello1
     *
     * @return
     */
    @GetMapping("/hello1")
    public String hello1() {
        try {
            retryableCustomerClient.getCustomer(12L);
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage() + ", 类型: " + e.getClass().getName());
            e.printStackTrace();
        }
        return Thread.currentThread().getStackTrace()[1].getMethodName();
    }

}
