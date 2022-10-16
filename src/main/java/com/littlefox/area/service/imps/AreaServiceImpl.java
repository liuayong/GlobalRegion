package com.littlefox.area.service.imps;

import cn.hutool.core.lang.Dict;
import com.littlefox.area.dao.AreaMapper;
import com.littlefox.area.enums.RedisKey;
import com.littlefox.area.model.Area;
import com.littlefox.area.service.AreaService;
import com.littlefox.area.utils.RedisUtil;
import com.mexue.middle.school.util.Validator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Override
    public List<Area> selectAreaList(String id, String level, String langType) {
        
        String key = MessageFormat.format(RedisKey.KEY_AREA, "all");
        
        List<Area> areas = redisUtil.get(key, List.class, Area.class);
        System.out.println("areas = " + areas);
        if (!Validator.empty(areas)) {
            return areas;
        }
        
        Map<String, String> params = new HashMap<String, String>(4) {{
            put("id", id);
            put("len", String.valueOf(id.length()));
            put("level", level);
            put("langType", langType);
        }};
        
        areas = areaMapper.selectAreaList(params);
        System.out.println("查询DB操作");
        redisUtil.set(key, areas);
        return areas;
    }
}
