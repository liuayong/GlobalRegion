package com.littlefox.area.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/js")
public class JsController {
    
    @Resource
    private HttpServletRequest request;
    
    @GetMapping
    public String js() {
        return "area/js";
    }
    
    @GetMapping("hdr")
    public String hdr() {
        return "area/hdr";
    }
    
    
    @GetMapping("/regex")
    public String regex() {
        return "area/regex";
    }
    
    // com.littlefox.area.Application.MyMvcConfig
    @GetMapping("/index")
    public String index() {
        return "area/index";
    }
}
