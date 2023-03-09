package com.hspedu.json.gson;


import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        
    
    }
    
    public static void jsonToMap(String json) {
        //Gson gson = new Gson();
        
        // https://blog.csdn.net/u012869196/article/details/80843180
        // https://blog.csdn.net/qq_18402475/article/details/80005921
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        
        
        // Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $
        //List<Person> persons = gson.fromJson(json, new TypeToken<List<Person>>() {
        //}.getType());//对于不是类的情况，用这个参数给出
        //for (Person person : persons) {
        //    System.out.println(person);
        //}
        Person person = gson.fromJson(json, Person.class);
        log.info("person={}", person);
        
        // TODO Auto-generated method stub
        Gson gson2 = new Gson();
        Map<String, String> maps = gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        
        //for (Map.Entry<String, String> entry : maps.entrySet()) {
        //    System.out.println("key: " + entry.getKey() + "  " + "value: " + entry.getValue());
        //}
        System.out.println("maps = " + maps);
    }
}
