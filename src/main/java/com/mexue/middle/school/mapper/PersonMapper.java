package com.mexue.middle.school.mapper;

import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.entity.PersonExample;
import com.mexue.middle.school.entity.Student;
import com.mexue.middle.school.search.PersonSearch;

import java.util.List;

public interface PersonMapper extends BaseMapper<Person> {
    int countByExample(PersonExample example);
    
    List<Person> query(PersonSearch personSearch);
}