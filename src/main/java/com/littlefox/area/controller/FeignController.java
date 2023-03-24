package com.littlefox.area.controller;

import com.littlefox.area.biz.controller.client.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/25
 **/
public class FeignController {
    
    @Autowired
    private HelloFeignClient helloFeignClient;
    
    @GetMapping("/hello")
    public String hello() {
        return helloFeignClient.hello();
    }
}
