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

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JsonToObjectTest {
    
    
    @Test
    public void jsonToJavaBean() {
    
    
    }
    
    @Test
    public void jsonToList() {
    }
    
    @Test
    public void jsonToMap() {
    }
}