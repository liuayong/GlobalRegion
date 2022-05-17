package com.littlefox.area.controller;

import com.littlefox.area.config.MsgUtil;
import com.littlefox.area.model.Area;
import com.littlefox.area.service.AreaService;
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

/**
 * 地区Controller
 *
 * @author rockychen
 */
@Controller
@RequestMapping("/test1")
public class Test0518Controller {
    
    @Resource
    private AreaService areaService;
    
    @Autowired
    private HttpServletRequest request;
    
    @RequestMapping("lang")
    @ResponseBody
    public String language(String l) {
        if (l == null) {
            l = "zh_CN";
        }
        // zh_CN,en_US,zh_TW
        String[] s = l.split("_");
        
        System.out.println("s = " + Arrays.toString(s));
        
        LocaleContextHolder.setLocale(new Locale(s[0], s[1]));
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println("locale = " + locale);
        
        String msgKey = request.getParameter("key");
        if (msgKey == null) {
            msgKey = "name";
        }
        
        String message = MsgUtil.get(msgKey);
        return message;
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
