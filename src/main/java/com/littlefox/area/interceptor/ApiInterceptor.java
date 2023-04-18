package com.littlefox.area.interceptor;


import com.littlefox.area.utils.TraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project: blog
 * @Description
 * @Author: Administrator
 * @Create: 2023/4/18
 **/
public class ApiInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("[{}] method={}, ApiInterceptor拦截器 : {}", TraceUtil.getLineNumber(), TraceUtil.getMethodName(),
                this.getClass().getName());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("[{}] method={}, ApiInterceptor拦截器 : {}", TraceUtil.getLineNumber(), TraceUtil.getMethodName(),
                this.getClass().getName());
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("[{}] method={}, ApiInterceptor拦截器 : {}", TraceUtil.getLineNumber(), TraceUtil.getMethodName(),
                this.getClass().getName());
        super.afterCompletion(request, response, handler, ex);
    }
}
