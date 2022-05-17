package demo;

import com.littlefox.area.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * https://www.jianshu.com/p/d148c996b744
 */
public class TestMapBeanConvert {
    
    private static Logger LOGGER = LoggerFactory.getLogger(TestMapBeanConvert.class);
    
    // 将map转为bean
    // https://iowiki.com/java_beanutils/data_type_conversions_beanutils_and_convertutils.html
    //https://www.cnblogs.com/jing1617/p/7007580.html
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clz) {
        
        T bean = null;
        
        try {
            bean = clz.newInstance();
            // 遍历map中的key，若bean中有这个属性(key)，将key对应的value赋值给bean对应的属性
            //org.apache.commons.beanutils.BeanUtils.populate
            BeanUtils.populate(bean, map); // 将map转为bean
            
        } catch (Exception e) {
            LOGGER.info("ERROR ------> " + e.getMessage());
        }
        
        return bean;
    }
    
    // 将bean转为map
    
    public static Map beanToMap(Object bean) {
        
        Map<String, String> map = null;
        
        try {
            map = BeanUtils.describe(bean); // 将bean转为map
        } catch (Exception e) {
            LOGGER.info("ERROR ------> " + e.getMessage());
        }
        
        return map;
        
    }
    
    public static void main(String[] args) {
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("name", "六月");
        
        map.put("age", 6);
        
        System.err.println("-------------------- from map to bean --------------------");
        
        Student user = mapToBean(map, Student.class);
        System.err.println(user.toString());
        
        System.err.println("-------------------- from bean to map --------------------");
        
        Map<String, String> toMap = beanToMap(user);
        
        for (Entry<String, String> entry : toMap.entrySet()) {
            System.out.println("key=" + entry.getKey() + " and value=" + entry.getValue());
        }
    }
}
