package com.hspedu.json.jackson;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.entity.PersonCopy;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PersonSerialize extends JsonSerializer<Person> {
    
    
    @Override
    public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        System.out.println("o = " + person);
        if ("刘阿勇".equals(person.getName())) {
            int rand = new Random().nextInt(100);
            jsonGenerator.writeString("刘阿勇-" + rand);
        } else {
            //jsonGenerator.writeObject(person);
            
            jsonGenerator.writeString(JSON.toJSONString(person));
            
            //jsonGenerator.writeObject(PersonCopy.convert(person));
            
            //Map<String, Object> map = new HashMap<>();
            //map.put("id", person.getId());
            //map.put("name", person.getName());
            //map.put("age", "18");
            //map.put("weChat", "851298348");
            //jsonGenerator.writeObject(map);
            
            //jsonGenerator.writeString(new ObjectMapper().writeValueAsString(person));
            
        }
        
    }
}
