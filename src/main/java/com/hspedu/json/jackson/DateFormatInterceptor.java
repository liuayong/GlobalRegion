package com.hspedu.json.jackson;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring.main.allow-bean-definition-overriding=true
 */
 // @Component
public class DateFormatInterceptor extends HandlerInterceptorAdapter {  // implements HandlerInterceptor
// public class DateFormatInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String requestURI = request.getRequestURI();
//            System.err.println(requestURI);
            String[] newUrls = new String[]{
                    "/bigElephant/teacher/learning/v2/",
                    "/bigElephant/director/",
                    "/lyy/",
            };
            for (String newUrl : newUrls) {
                if (requestURI.startsWith(newUrl)) {
                    DateTypeUtil.dateTypeThreadLocal.set(DateTypeUtil.DateType.ISO_LOCAL_DATE_TIME);
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        DateTypeUtil.dateTypeThreadLocal.remove();
    }
}
