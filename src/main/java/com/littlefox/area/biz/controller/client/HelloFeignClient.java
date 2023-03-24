package com.littlefox.area.biz.controller.client;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/25
 **/
@org.springframework.cloud.openfeign.FeignClient(value = "eureka-client")
public interface HelloFeignClient {
    @GetMapping("/hello")
    String hello();
}
