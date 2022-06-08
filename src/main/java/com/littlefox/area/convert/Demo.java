package com.littlefox.area.convert;

import com.littlefox.area.model.User;

import java.lang.reflect.Field;
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        User user = new User();
        Map<String, Object> keyValues = new HashMap<>();
        
        user.setPassWord("password");
        user.setComments("test method!");
        user.setUserName("wang shisheng");
        user.setUserCode(2018998770);
        
        Map<? super Object, ? super Object> map = new HashMap<>();
        map.put("passWord", "W$%^@#$@#%");
        map.put("userName", "刘阿勇");
        map.put("cmt", "转换注释");
        map.put("createTime", new Date());
        map.put("userCode", 32323);
        
        StandardMappingImpl standardMapping1 = new StandardMappingImpl("passWord", "passWord");
        StandardMappingImpl standardMapping2 = new StandardMappingImpl("cmt", "comments");
        List<Mapping<String, String>> standardMappings = Arrays.asList(standardMapping1, standardMapping2);
        
        System.out.println(standardMappings);
        
        standardMappings.stream().forEach(mapping -> {
            Object source = mapping.getSource();
            System.out.println(source + "," + source.getClass().getName());
            System.out.println(mapping.getTarget());
            
        });
    }
}
