package com.littlefox.area.controller;

import com.littlefox.area.config.MsgUtil;
import com.littlefox.area.model.Area;
import com.littlefox.area.service.AreaService;
import com.littlefox.area.vo.GetCarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
     * 查询国家省份地区下拉
     *
     * @param areaId 区域ID，level为0时可以为空
     * @param level  区域的级别，标注国家、省份、地区、城市等
     * @return List<Area>
     */
    @RequestMapping(value = "/selectAreaList.json", method = RequestMethod.POST)
    @ResponseBody
    public List<Area> selectAreaList(@ModelAttribute("areaId") String areaId, @ModelAttribute("level") String level) {
        String langType = "zh_CN";
        langType = "en_US";
        return areaService.selectAreaList(areaId, level, langType);
    }
    
}
