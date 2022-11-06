package com.littlefox.area.service.imps;

import cn.hutool.core.lang.Dict;
import com.littlefox.area.dao.AreaMapper;
import com.littlefox.area.enums.RedisKey;
import com.littlefox.area.model.Area;
import com.littlefox.area.properties.GirlProperties;
import com.littlefox.area.properties.ProfileConfig;
import com.littlefox.area.service.AreaService;
import com.littlefox.area.utils.RedisUtil;
import com.mexue.middle.school.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

/**
 * 地区Service
 *
 * @author rockychen
 */
@Service
public class AreaServiceImpl implements AreaService {
    
    @Resource
    private AreaMapper areaMapper;
    
    @Resource
    private RedisUtil redisUtil;
    
    
    // https://blog.csdn.net/qq_27818541/article/details/105719962
    //@Value("${spring.profiles.active}")
    private String[] env;
    
    @Value("${server.port}")
    private Integer appPort;
    
    @Autowired
    private GirlProperties girlProperties;
    
    @Autowired
    private ProfileConfig profileConfig;
    
    
    @Override
    public List<Area> selectAreaList(String id, String level, String langType) {
        System.out.println("env = " + Arrays.toString(env));
        System.out.println("当前运行环境 = " + profileConfig.getActiveProfile());
        System.out.println("girlProperties = " + girlProperties);
        System.out.println("appPort = " + appPort);
        
        
        String key = MessageFormat.format(RedisKey.KEY_AREA, "all");
        List<Area> areas = null ;
        
        // 开发环境 使用redis
        if("dev".equalsIgnoreCase(profileConfig.getActiveProfile())) {
            areas = redisUtil.get(key, List.class, Area.class);
            System.out.println("areas = " + areas);
            if (!Validator.empty(areas)) {
                return areas;
            }
        }
        
        Map<String, String> params = new HashMap<String, String>(4) {{
            put("id", id);
            put("len", String.valueOf(id.length()));
            put("level", level);
            put("langType", langType);
        }};
        
        
        areas = areaMapper.selectAreaList(params);
        System.out.println("查询DB操作");
        
        // 开发环境 使用redis
        if("dev".equalsIgnoreCase(profileConfig.getActiveProfile())) {
            redisUtil.set(key, areas);
        }
        
        return areas;
    }
}
