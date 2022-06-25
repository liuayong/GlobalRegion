package com.mexue.middle.school.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mexue.middle.school.common.PageResult;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.mapper.PersonMapper;
import com.mexue.middle.school.search.PersonSearch;
import com.mexue.middle.school.service.PersonService;
import com.mexue.middle.school.util.BeanUtil;
import com.mexue.middle.school.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {
    
    private PersonMapper personMapper;
    
    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
        this.baseMapper = personMapper;
    }
    
    
    @Override
    public PageResult<PersonVo> query(PersonSearch personSearch) {
        Page<Object> page = PageHelper.startPage(personSearch.getCurPage(), personSearch.getPageSize());
        List<Person> personList = personMapper.query(personSearch);
        List<PersonVo> personVoList = personList.stream()
                .map(person -> BeanUtil.convert(person, PersonVo.class))
                .collect(Collectors.toList());
        
        return new PageResult<>(page.getTotal(), personVoList);
    }
    
    @Override
    public List<PersonVo> queryList(PersonSearch personSearch) {
        List<Person> personList = personMapper.query(personSearch);
        List<PersonVo> personVoList = personList.stream()
                .map(person -> BeanUtil.convert(person, PersonVo.class))
                .collect(Collectors.toList());
        
        return personVoList;
    }
}
