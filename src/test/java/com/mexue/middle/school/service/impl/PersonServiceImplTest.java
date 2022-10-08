package com.mexue.middle.school.service.impl;

import com.littlefox.area.Application;
import com.mexue.middle.school.common.PageResult;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.entity.PersonCopy;
import com.mexue.middle.school.search.PersonSearch;
import com.mexue.middle.school.service.PersonService;
import com.mexue.middle.school.service.StudentService;
import com.mexue.middle.school.vo.PersonVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonServiceImplTest {
    
    @Resource
    private PersonService personService;
    
    @Test
    public void query() {
        PersonSearch personSearch = new PersonSearch();
        PageResult<PersonVo> pageResult = personService.query(personSearch);
        List<PersonVo> items = pageResult.getItems();
        System.out.println(items.size());
        
    }
    
    @Test
    public void queryList() {
        PersonSearch personSearch = new PersonSearch();
        List<PersonVo> personVos = personService.queryList(personSearch);
        System.out.println(personVos.size());
    }
    
    
    @Test
    public void selectByPrimaryKey() {
        Person person = personService.selectByPrimaryKey(9);
        System.out.println(person);
        
        PersonCopy personCopy = PersonCopy.convert(person);
        System.out.println(personCopy);
        System.out.println(personCopy.toString2());
        
    }
}