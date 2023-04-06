package com.littlefox.area.controller;

import com.littlefox.area.biz.controller.client.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/25
 **/
@RestController("/feign")
public class FeignController {

    @Autowired
    private HelloFeignClient helloFeignClient;

    @GetMapping("/hello")
    public String hello() {
        return helloFeignClient.hello();
    }
}
