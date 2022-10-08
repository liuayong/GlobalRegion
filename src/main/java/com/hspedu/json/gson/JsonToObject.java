package com.hspedu.json.gson;


import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.vo.PersonVo;


public class JsonToObject {
    
    /**
     * json to javabean
     *
     * @param json
     */
    public static void jsonToJavaBean(String json) {
        Gson gson = new Gson();
        Person person = gson.fromJson(json, Person.class);//对于javabean直接给出class实例
        System.out.println(person.toString());
    }
    
    /**
     * json字符串转List集合
     */
    
    public static void jsonToList(String json) {
        
        Gson gson = new Gson();
        List<PersonVo> persons = gson.fromJson(json, new TypeToken<List<PersonVo>>() {
        }.getType());//对于不是类的情况，用这个参数给出
        for (PersonVo person : persons) {
            System.out.println(person);
        }
    }
    
    public static void jsonToMap(String json) {
        // TODO Auto-generated method stub
        Gson gson = new Gson();
        Map<String, String> maps = gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        
        //for (Map.Entry<String, String> entry : maps.entrySet()) {
        //    System.out.println("key: " + entry.getKey() + "  " + "value: " + entry.getValue());
        //}
        System.out.println("maps = " + maps);
    }
}
