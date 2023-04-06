package com.littlefox.area.service.imps;

import com.littlefox.area.model.User;
import com.littlefox.area.service.RetryableCustomerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class RetryableCustomerClientImpl implements RetryableCustomerClient {

    private static int count = 1;


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
        if (count > 0 && count < 7) {
            log.info(LocalTime.now() + String.format(" do something... count = %s", count));
            throw new RemoteAccessException(String.format("i = %s, RPC调用异常, 前N次抛出异常", count++));
        }

        Random random = new Random();
        int rand = random.nextInt(10000);
        log.info("rand = {}", rand);
        // return  Math.round() * 100;
    }


    //    命令式实现-支持动态重试策略 https://blog.csdn.net/m0_74931226/article/details/128452046
    public Optional<User> retrieveProduct(String productCode) {
        // 比如交通意外险重试5次，其他保险产品重试2次
        int maxAttempts = productCode.startsWith("JIAOTONG") ? 5 : 2;

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

        return null;
    }

    public User getUser(String productCode) {
        User user = new User();
        user.setUserName(productCode);
        return user;
    }


    @Recover
    public void recover(RemoteAccessException e) {
        log.info(e.getMessage());
    }
}
