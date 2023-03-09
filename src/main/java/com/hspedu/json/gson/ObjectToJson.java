package com.hspedu.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class ObjectToJson {
    
    /**
     * javabean to json
     *
     * @param person
     * @return
     */
    public static String javabeanToJson(Person person) {
        Gson gson = new Gson();
        String json = gson.toJson(person);
        return json;
    }
    
    /**
     * list to json
     *
     * @param list
     * @return
     */
    public static String listToJson(List<PersonVo> list) {
        
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
    
    /**
     * map to json
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, Person> map) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        String newValue1 = gson.toJson(new Date(System.currentTimeMillis()), Date.class);
        String newValue2 = gson.toJson(System.currentTimeMillis());
        log.info("newValue1 = {}, newValue2 = {}", newValue1, newValue2);
        //Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }
    
    
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        String newValue1 = gson.toJson(new Date(System.currentTimeMillis()), Date.class);
        String newValue2 = gson.toJson(System.currentTimeMillis());
        log.info("newValue1 = {}, newValue2 = {}", newValue1, newValue2);
    
        Person person = new Person();
        person.setName("阿勇");
        person.setCreateDate(new Date());
    
        String s = gson.toJson(person);
        System.out.println(s);
    }
}
