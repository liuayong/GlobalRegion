package com.mexue.middle.school.service;


import com.mexue.middle.school.common.PageResult;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.search.PersonSearch;
import com.mexue.middle.school.vo.PersonVo;

import java.util.List;

public interface PersonService extends BaseService<Person> {
    PageResult<PersonVo> query(PersonSearch personSearch);
    
    List<PersonVo> queryList(PersonSearch personSearch);
}
