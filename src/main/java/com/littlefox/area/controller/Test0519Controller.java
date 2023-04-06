package com.littlefox.area.controller;

import com.alibaba.fastjson.JSONObject;
import com.littlefox.area.service.AreaService;
import com.littlefox.area.vo.GetCarRequest;
import com.littlefox.area.vo.GetCarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 地区Controller
 *
 * @author rockychen
 */
@Controller
@RequestMapping("/test19")
public class Test0519Controller {
    
    @Resource
    private AreaService areaService;
    
    @Autowired
    private HttpServletRequest request;


    /**
     * /test19/snake
     * @return
     */
    @RequestMapping(value = "/snake")
    @ResponseBody
    public GetCarResponse snake() {
        GetCarResponse getCarResponse = new GetCarResponse();
        getCarResponse.setCardId("123123123");
        getCarResponse.setTestName("刘阿勇");
        getCarResponse.setName("liuayong");
        getCarResponse.setOrderCard(UUID.randomUUID().toString());
        return getCarResponse;
    }
    
    /**
     * /test19/queryCard
     * 查询卡券
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/queryCard")
    @ResponseBody
    public Object queryCard(HttpServletRequest request) {
        // 取出所有的map参数
        //Map<String, String> paramMap = ServletUtil.getParamMap(request);
        
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> paramMap = new HashMap<>();
        for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
            paramMap.put(stringEntry.getKey(), stringEntry.getValue()[0]);
        }
        String jsonString;
        if ("json".equalsIgnoreCase(request.getParameter("act"))) {
            GetCarRequest carobj = new GetCarRequest();
            carobj.setCardId("cardId123123");
            carobj.setTestName("刘阿勇");
            carobj.setUserName("liuayong");
            carobj.setOrderCard("No.orderCarddddd");
            jsonString = JSONObject.toJSONString(carobj);
        } else {
            // 转换为json字符串
            jsonString = JSONObject.toJSONString(paramMap);
            
        }
        
        // 转换为java对象
        GetCarRequest getCarRequest = JSONObject.parseObject(jsonString, GetCarRequest.class);
        
        return getCarRequest;
    }
    
}
