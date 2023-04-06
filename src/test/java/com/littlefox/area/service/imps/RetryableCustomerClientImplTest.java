package com.littlefox.area.service.imps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RetryableCustomerClientImplTest {
    @Resource
    private RetryableCustomerClientImpl retryableCustomerClient;

    @Test
    public void getCustomer() {
        retryableCustomerClient.getCustomer(2L);
    }
}