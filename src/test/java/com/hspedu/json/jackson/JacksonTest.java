package com.hspedu.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hspedu.json.PersonServiceData;
import com.hspedu.json.gson.JsonToObject;
import com.hspedu.json.gson.ObjectToJson;
import com.littlefox.area.Application;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.vo.PersonVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JacksonTest extends PersonServiceData {
    
    
    /**
     * deserialize
     *
     * @throws Exception
     */
    @Test
    public void javabeanToJson() throws Exception {
        Person person = selectByPrimaryKey(1);
        String jsonStr = JacksonUtil.custom(person);
        System.out.println("【jackson】serialize: " + jsonStr);
        Person deserialize = JacksonUtil.deserialize(jsonStr, Person.class);
        System.out.println("【jackson】deserialize : " + deserialize);
    }
    
    @Test
    public void personSerializeTest() {
        Person person = selectByPrimaryKey(1);
        String jsonStr = JacksonUtil.custom(person);
        System.out.println("【jackson】PersonSerialize: " + jsonStr);
        
        person = selectByPrimaryKey(2);
        jsonStr = JacksonUtil.custom(person);
        System.out.println("【jackson】PersonSerialize: " + jsonStr);
    }
    
    
    @Test
    public void test1() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 12);
        map.put("name", "刘阿勇");
        map.put("age", "18");
        map.put("birthday", new Date());
        String string = new ObjectMapper().writeValueAsString(map);
        String custom = JacksonUtil.custom(map);
        System.out.println(string);
        System.out.println(custom);
    }
    
    
    @Test
    public void listToJson() {
        List<PersonVo> personVoList = query();
        String s = JacksonUtil.custom(personVoList);
        System.out.println("【jackson】listToJson: " + s);
        JsonToObject.jsonToList(s);
    }
    
    @Test
    public void mapToJson() throws Exception {
        Map map = personMap();
        
        String s = ObjectToJson.mapToJson(map);
        System.out.println("【jackson】mapToJson: " + s);
        JsonToObject.jsonToMap(s);
    }
}