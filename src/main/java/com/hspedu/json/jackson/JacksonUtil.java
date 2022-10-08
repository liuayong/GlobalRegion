package com.hspedu.json.jackson;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mexue.middle.school.entity.Person;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class JacksonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * JSON字符串反序列化成Java对象
     *
     * @param json
     * @return
     */
    public static <T> T deserialize(String json, Class<T> clzz) {
        T user = null;
        try {
            user = objectMapper.readValue(json, clzz);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    /**
     * Java对象序列化成JSON字符串
     *
     * @param obj
     * @return
     */
    public static <T> String custom(T obj) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
    
    /**
     * Jackson提供了JavaType，用来指明集合类型和泛型
     *
     * @param collectonClass
     * @param elementClasses
     * @return
     */
    public static JavaType getCollectionType(Class<?> collectonClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectonClass, elementClasses);
    }
    
    /**
     * 集合的反序列化
     *
     * @param jsonArray
     * @return
     */
    public static <T> List<T> databinds(String jsonArray, Class<T> tClass) {
        JavaType type = getCollectionType(List.class, tClass);
        java.util.List<T> list = null;
        try {
            list = objectMapper.readValue(jsonArray, type);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static void main(String[] args) throws JsonProcessingException {
        //JSON字符串反序列化成Java对象
        String json = "{\"name\":\"Rain\",\"id\":4405,\"age\":28,\"createDate\":\"2018-01-25 12:17:22\",\"phone\":\"13891701101\"}";
        Person user = deserialize(json, Person.class);
        System.out.println("json转对象: " + user.toString());
        
        //Java对象序列化成JSON字符串
        //Person user = new Person();
        user.setId(10086L);
        user.setName("Bob");
        user.setAge(Short.MIN_VALUE);
        user.setCreateDate(new Date());
        System.out.println("对象转json: " + custom(user));
        
        //集合的反序列化和序列化
        String jsonArray = "[{\"name\":\"Rain\",\"id\":4405,\"age\":28,\"createDate\":\"2018-01-25 12:17:22\",\"phone\":\"13891701102\"},"
                + "{\"name\":\"Jim\",\"id\":4406,\"age\":26,\"createDate\":\"2018-01-25 12:17:22\",\"phone\":\"13891701103\"}]";
        
        List<Person> userList = databinds(jsonArray, Person.class);
        for (Person userEntity : userList) {
            System.out.println(userEntity.toString());
        }
        
        //集合的序列化
        System.out.println("集合的序列化:" + objectMapper.writeValueAsString(userList));
        
    }
    
    
}
