package com.mexue.middle.school.service;

import com.mexue.middle.school.entity.Teacher;

import java.util.List;

public interface TeacherService {
    
    int deleteByPrimaryKey(Integer id);
    
    int insert(Teacher record);
    
    int insertSelective(Teacher record);
    
    Teacher selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Teacher record);
    
    int updateByPrimaryKey(Teacher record);
    
    
    List<Teacher> queryList(Teacher record);
    
}
