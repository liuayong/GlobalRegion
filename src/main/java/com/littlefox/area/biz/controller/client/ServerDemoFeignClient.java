package com.littlefox.area.biz.controller.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/25
 **/
@FeignClient(name = "ServerDemoFeignClient", url = "http://localhost:8080")
public interface ServerDemoFeignClient {
    @GetMapping("/server/demo/hello/{username}")
    public String hello(@PathVariable("username") String username,
                        @RequestParam("msg") String msg);
}
