package com.littlefox.area.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @Project: blog
 * @Description
 * @Author: Administrator
 * @Create: 2023/4/3
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle 拦截器 : {}" ,                this.getClass().getName());
        if (ObjectUtils.isEmpty(request.getSession().getAttribute("LOGIN_KEY"))) {

            String redirect = ObjectUtils.isEmpty(request.getQueryString()) ? request.getRequestURI() :
                    request.getRequestURI() + URLEncoder.encode("?" + request.getQueryString(), "UTF-8");   // 获取编码
            response.sendRedirect("/admin?redirect=" + redirect);
            return false;
        }
        return true;
    }
}
