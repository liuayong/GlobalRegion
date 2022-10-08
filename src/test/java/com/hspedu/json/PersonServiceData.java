package com.hspedu.json;

import com.littlefox.area.utils.BeanUtil;
import com.mexue.middle.school.common.PageResult;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.search.PersonSearch;
import com.mexue.middle.school.service.PersonService;
import com.mexue.middle.school.vo.PersonVo;

import javax.annotation.Resource;
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
        Person person = personService.selectByPrimaryKey(id);
        return person;
    }
    public Person selectByPrimaryKey() {
        return selectByPrimaryKey(9);
    }
    
    public Map personMap() throws Exception {
        Person person = selectByPrimaryKey();
        return BeanUtil.beanToMap(person);
    }
}
