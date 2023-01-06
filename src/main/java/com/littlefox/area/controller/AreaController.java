package com.littlefox.area.controller;

import com.littlefox.area.config.MsgUtil;
import com.littlefox.area.model.Area;
import com.littlefox.area.properties.GirlProperties;
import com.littlefox.area.properties.ProfileConfig;
import com.littlefox.area.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 地区Controller
 *
 * @author rockychen
 */
@Controller
@RequestMapping("/area")
@Slf4j
public class AreaController {
    
    @Resource
    private AreaService areaService;
    
    @Autowired
    private HttpServletRequest request;
    
    // https://blog.csdn.net/qq_27818541/article/details/105719962
    //@Value("${spring.profiles.active}")
    private String[] env;
    
    @Value("${server.port}")
    private Integer appPort;
    
    @Autowired
    private GirlProperties girlProperties;
    
    @Autowired
    private ProfileConfig profileConfig;
    
    
    @RequestMapping("lang")
    @ResponseBody
    public Map<String, Object> language(String l) {
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
        
        HttpSession session = request.getSession();
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msgKey", msgKey);
        map.put("message", message);
        map.put("locale", locale);
        map.put("url", request.getRequestURL());
        map.put("method", request.getMethod());
        map.put("ip", request.getRemoteAddr());
        map.put("ServerName", request.getServerName());
        map.put("SessionID", session.getId());
        
        map.put("LocalPort", request.getLocalPort());
        map.put("RemotePort", request.getRemotePort());
        map.put("ServerPort", request.getServerPort());
        
        map.put("env", Arrays.toString(env));
        map.put("currEnv", profileConfig.getActiveProfile());
        map.put("girlProperties", girlProperties);
        map.put("appPort", appPort);
        
        //url
        log.info("url={}", request.getRequestURL());
        //method
        log.info("method={}", request.getMethod());
        //ip
        log.info("ip={}", request.getRemoteAddr());
        
        
        return map;
    }
    
    /**
     * 查询国家省份地区下拉
     *
     * @param areaId 区域ID，level为0时可以为空
     * @param level  区域的级别，标注国家、省份、地区、城市等
     * @return List<Area>
     */
    @RequestMapping(value = "/selectAreaList.json")
    @ResponseBody
    public List<Area> selectAreaList(@ModelAttribute("areaId") String areaId, @ModelAttribute("level") String level) {
        String langType = "zh_CN";
        langType = "en_US";
        return areaService.selectAreaList(areaId, level, langType);
    }
    
}
