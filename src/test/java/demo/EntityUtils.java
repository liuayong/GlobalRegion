package demo;

import com.littlefox.area.model.Employee;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityUtils {
    
    
    public static void main(String[] args) {
        Map<String, Object> map = MapUtil.buildMap();
        
        Object o = mapToEntity(map, Employee.class);
    }
    
    /**
     * 实体类转Map
     *
     * @param object
     */
    public static Map entityToMap(Object object) {
        Map map = new HashMap();
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                Object o = field.get(object);
                map.put(field.getName(), o);
                field.setAccessible(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        return map;
    }
    
    /*** Map转实体类
     *@parammap 需要初始化的数据，key字段必须与实体类的成员名字一样，否则赋值为空
     *@paramentity 需要转化成的实体类
     *@return
     
     */
    
    public static <T> T mapToEntity(Map map, Class entity) {
        T t = null;
        try {
            t = (T) entity.newInstance();
            for (Field field : entity.getDeclaredFields()) {
                if (map.containsKey(field.getName())) {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    Object object = map.get(field.getName());
                    if (object != null && field.getType().isAssignableFrom(object.getClass())) {
                        field.set(t, object);
                    }
                    field.setAccessible(flag);
                }
            }
            return t;
        } catch (InstantiationException e) {//TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {//TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }
    
}