package demo;

import com.littlefox.area.model.Employee;
import com.littlefox.area.utils.BeanUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

// https://blog.csdn.net/qq_44377709/article/details/109791413
public class MapToPojo {
    
    
    @Test
    public void mapToBean() {
        
        Map<String, Object> map = MapUtil.buildMap();
        try {
            Employee employee = (Employee) BeanUtil.mapToBean(map, Employee.class);
            System.out.print(employee.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
