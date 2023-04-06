package com.littlefox.area.controller;

import com.littlefox.area.model.User;
import com.littlefox.area.service.imps.RetryableCustomerClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/retry")
public class RetryController {
    @Resource
    private RetryableCustomerClientImpl retryableCustomerClient;
    @Resource
    private HttpServletRequest request;

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


    /**
     * http://localhost:8080/retry/reset?count=1
     *
     * @return
     */
    @GetMapping("/reset")
    public String resetCount() {
        String count = request.getParameter("count");
        int intCnt = 0;
        if (count != null) {
            intCnt = Integer.parseInt(count);
            RetryableCustomerClientImpl.count = intCnt;
        }
        String comments = Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", 数量: " + RetryableCustomerClientImpl.count;

        return comments;
    }

    /**
     * http://localhost:8080/retry/hello2?count=1&productCode=
     *
     * @return
     */
    @GetMapping("/hello2")
    public User hello2() {
        User user = null;
        try {
            String count = request.getParameter("count");
            int intCnt = 0;
            if (count != null) {
                intCnt = Integer.parseInt(count);
            }
            String productCode = request.getParameter("productCode");
            if (productCode == null) {
                productCode = "JIAOTONG";
            }
            user = retryableCustomerClient.retrieveProduct(productCode);

        } catch (Exception e) {
            System.out.println("e = " + e.getMessage() + ", 类型: " + e.getClass().getName());
            e.printStackTrace();
        }
        if (user == null) {
            user = new User();
        }
        String comments = Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", 数量: " + RetryableCustomerClientImpl.count;

        user.setComments(comments);
        return user;
    }

}
