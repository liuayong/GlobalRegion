package demo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.littlefox.area.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 版权声明：本文为CSDN博主「qingcyb」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qingcyb/article/details/106344983
 * 转换实体
 */
public class Convert {
    
    
    /**
     * 将map转换为VO
     *
     * @param list 转换前map集合
     * @return 转化后VO集合
     */
    public List<Student> convertMapToVo(List<Map<String, Object>> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        // 如果有字段在VO不存在，不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Student> stuList = list.stream()
                .map(stuMap -> objectMapper.convertValue(stuMap, Student.class))
                .collect(Collectors.toList());
        return stuList;
    }
    
    public static void main(String[] args) {
        Convert test = new Convert();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 01);
        map1.put("age", 18);
        map1.put("name", "何刚");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 02);
        map2.put("age", 23);
        map2.put("name", "叶英");
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        List<Student> students = test.convertMapToVo(list);
        students.forEach(System.out::println);
        
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = MapUtil.buildMap();
        Student student = objectMapper.convertValue(map, Student.class);
        System.out.println(student);
    }
}
