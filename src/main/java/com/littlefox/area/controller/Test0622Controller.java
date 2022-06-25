package com.littlefox.area.controller;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.littlefox.area.model.Shop;
import com.littlefox.area.service.AreaService;
import com.littlefox.area.vo.GetCarRequest;
import com.littlefox.area.vo.GetCarResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 地区Controller
 *
 * @author rockychen
 */
@Controller
@RequestMapping("/test0622")
@Slf4j
public class Test0622Controller {
    
    @Resource
    private AreaService areaService;
    
    @Autowired
    private HttpServletRequest request;
    
    
    @RequestMapping(value = "/t1")
    @ResponseBody
    public Object tst1() {
        Map<String, String> paramMap = ServletUtil.getParamMap(request);
        System.out.println(" tst1 paramMap = " + paramMap);
        String method = request.getMethod();
        System.out.println("method = " + method);
        String city = request.getParameter("city");
        System.out.println("city = " + city);
        
        Shop shop = new Shop();
        shop.setName("苹果手机");
        shop.setCategory("Iphone");
        shop.setCreateTime(new Date());
        Random random = new Random();
        shop.setPrice(random.nextDouble());
        
        
        try {
            int millis = 1000 * 150;
            Thread.sleep(millis);   // 延时50s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("shop = " + shop);
        return shop;
    }
    
    
    @RequestMapping(value = "/api")
    @ResponseBody
    public Object hutuApi() {
        log.info("======================{}====================", "hutuApi");
        Map<String, String> paramMap = ServletUtil.getParamMap(request);
        System.out.println("paramMap = " + paramMap);
        String url = request.getParameter("url");
        if (url == null) {
            //url = "/test0622/t1";
            url = "http://localhost:8080/test0622/t1";
        }
        System.out.println("url = " + url);
        
        Map<String, Object> map = new HashMap<>();
        map.put("time", System.currentTimeMillis());
        map.put("name", "刘阿勇");
        //String content = HttpUtil.get(url);
        String content = null;
        
        try {
            //链式构建请求
            content = HttpRequest.get(url)
                    .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                    .form(map)//表单内容
                    .timeout(5000)//超时，毫秒
                    .execute().body();
        } catch (HttpException e) {
            System.out.println("出现异常: " + e.getClass().getName());
            e.printStackTrace();
            content = e.getMessage(); // 异常的错误消息
        }
        
        return content;
    }
    
    
    /**
     * http 01 快速入门
     *
     * @param url
     * @return
     */
    private String http1(String url) {
        //GET请求
        //String content = HttpUtil.get(url);
        //POST请求
        HashMap<String, Object> params = new HashMap<>();
        params.put("city", "北京");
        params.put("name", "刘阿勇");
        
        String content = HttpUtil.post(url, params);
        
        System.out.println("content = " + content);
        return content;
    }
    
}
