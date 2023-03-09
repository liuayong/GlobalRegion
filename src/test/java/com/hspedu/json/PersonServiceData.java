package com.hspedu.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.littlefox.area.utils.BeanUtil;
import com.mexue.middle.school.common.PageResult;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.search.PersonSearch;
import com.mexue.middle.school.service.PersonService;
import com.mexue.middle.school.vo.PersonVo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PersonServiceData {
    @Resource
    private PersonService personService;
    
    public List<PersonVo> query() {
        PersonSearch personSearch = new PersonSearch();
        PageResult<PersonVo> pageResult = personService.query(personSearch);
        List<PersonVo> items = pageResult.getItems();
        return items.subList(0, Math.min(3, items.size()));
    }
    
    
    public Person selectByPrimaryKey(int id) {
        
        Person person = null;
        if (personService != null) {
            person = personService.selectByPrimaryKey(id);
        } else {
            person = new Person();
            person.setName("刘阿勇-测试");
            person.setAge((short) 12);
            person.setOrigin("深圳坪山");
            person.setCreateDate(new Date());
        }
        return person;
    }
    
    public Person selectByPrimaryKey() {
        return selectByPrimaryKey(9);
    }
    
    public Map personMap() throws Exception {
        Person person = selectByPrimaryKey();
        
        // https://www.cnblogs.com/jokerjason/p/5724493.html
        //return BeanUtil.beanToMap(person);  // todo 刘阿勇  Date格式支持
        String dateformat = "yyyy-MM-dd HH:mm:ss";
        //JSON.toJSONStringWithDateFormat(person, dateformat, SerializerFeature.WriteDateUseDateFormat);
        
        Map<String, Object> a = (Map<String, Object>) JSON.toJSON(person);
        
        //Object o = JSON.toJSON(person);
        return a;
    }
}
