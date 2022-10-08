package com.hspedu.json.gson;

import com.littlefox.area.Application;
import com.littlefox.area.utils.BeanUtil;
import com.mexue.middle.school.common.PageResult;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.search.PersonSearch;
import com.mexue.middle.school.service.PersonService;
import com.mexue.middle.school.vo.PersonVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ObjectToJsonTest {
    @Resource
    private PersonService personService;
    
    public List<PersonVo> query() {
        PersonSearch personSearch = new PersonSearch();
        PageResult<PersonVo> pageResult = personService.query(personSearch);
        List<PersonVo> items = pageResult.getItems();
        return items.subList(0, Math.min(3, items.size()));
    }
    
    
    public Person selectByPrimaryKey() {
        Person person = personService.selectByPrimaryKey(9);
        return person;
    }
    
    
    public Map personMap() throws Exception {
        Person person = selectByPrimaryKey();
        return BeanUtil.beanToMap(person);
    }
    
    @Test
    public void javabeanToJson() throws Exception {
        Person person = selectByPrimaryKey();
        String s = ObjectToJson.javabeanToJson(person);
        System.out.println("javabeanToJson: " + s);
        JsonToObject.jsonToJavaBean(s);
    }
    
    
    @Test
    public void listToJson() {
        List<PersonVo> personVoList = query();
        String s = ObjectToJson.listToJson(personVoList);
        System.out.println("listToJson: " + s);
        JsonToObject.jsonToList(s);
    }
    
    @Test
    public void mapToJson() throws Exception {
        Map map = personMap();
        
        String s = ObjectToJson.mapToJson(map);
        System.out.println("mapToJson: " + s);
        JsonToObject.jsonToMap(s);
    }
}