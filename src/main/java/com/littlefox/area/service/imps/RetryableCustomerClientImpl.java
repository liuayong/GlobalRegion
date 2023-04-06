package com.littlefox.area.service.imps;

import com.littlefox.area.model.User;
import com.littlefox.area.service.RetryableCustomerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Random;

@Service
@Slf4j
public class RetryableCustomerClientImpl implements RetryableCustomerClient {

    public static int count = 1;


    /**
     * 声明式实现
     * 原文链接：https://blog.csdn.net/m0_74931226/article/details/128452046
     *
     * @param id
     * @return
     */
    // @Retryable(RemoteAccessException.class)
    @Retryable(value = RemoteAccessException.class, maxAttempts = 5, backoff = @Backoff(delay = 500L, maxDelay = 3000L, multiplier = 2, random = true))
    @Override
    public void getCustomer(Long id) {
        if (count > 0 && count < 3) {
            log.info(LocalTime.now() + String.format("【getCustomer】 count = %s", count));
            throw new RemoteAccessException(String.format("i = %s, RPC调用异常, 前N次抛出异常", count++));
        }

        Random random = new Random();
        int rand = random.nextInt(10000);
        log.info("rand = {}", rand);
        // return  Math.round() * 100;
    }


    //    命令式实现-支持动态重试策略 https://blog.csdn.net/m0_74931226/article/details/128452046
    public User retrieveProduct(String productCode) {
        // 比如交通意外险重试5次，其他保险产品重试2次
        int maxAttempts = productCode.startsWith("JIAOTONG") ? 10 : 3;

        // https://github.com/spring-projects/spring-retry/blob/main/src/main/java/org/springframework/retry/support/RetryTemplateBuilder.java
        // RetryTemplate retryTemplate = RetryTemplate.builder()
        //         .maxAttempts(3)
        //         .fixedBackoff(1000)
        //         .withListener(new MyRetryListener())
        //         .retryOn(RemoteAccessException.class)
        //         .build();

        // RetryTemplate retryTemplate = new  RetryTemplate();
        // retryTemplate.se
        //         retryTemplate.setMaxAttempts(maxAttempts);
        //         retryTemplate.setRetryOn(RuntimeException.class)
        //         retryTemplate.setExponentialBackoff(300L, 2, 5000L, true)
        //         retryTemplate.setBuild();
        // return retryTemplate.execute(arg -> this.getUser(productCode));


        // https://blog.csdn.net/z69183787/article/details/106876977
        RetryTemplate retryTemplate = new RetryTemplate();
        // retryTemplate.setM
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(maxAttempts));
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(3000);
        backOffPolicy.setMultiplier(2);
        backOffPolicy.setMaxInterval(15000);
        retryTemplate.setBackOffPolicy(backOffPolicy);


        User execute = retryTemplate.execute(arg -> this.getUser(productCode));
        return execute;
    }

    public User getUser(String productCode) {
        if (count > 0 && count < 7) {
            log.info(LocalTime.now() + String.format("【retrieveProduct】do something... count = %s", count));
            throw new RemoteAccessException(String.format("i = %s, RPC调用异常, getUser", count++));
        }

        Random random = new Random();
        int rand = random.nextInt(10000);

        User user = new User();
        user.setUserName(productCode);
        user.setUserCode(rand);
        log.info("user ={}", user);
        return user;
    }


    @Recover
    public void recover(RemoteAccessException e) {
        log.info(e.getMessage());
    }
}
