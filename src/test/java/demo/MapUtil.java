package demo;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
    
    public static Map<String, Object> buildMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("name", "张三");
        map.put("age", 30);
        return map;
    }
}
