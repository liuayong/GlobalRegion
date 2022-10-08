package com.hspedu.json.jackson;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DateFormatInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String requestURI = request.getRequestURI();
//            System.err.println(requestURI);
            String[] newUrls = new String[]{
                    "/bigElephant/teacher/learning/v2/",
                    "/bigElephant/director/",
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
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        DateTypeUtil.dateTypeThreadLocal.remove();
    }
}
