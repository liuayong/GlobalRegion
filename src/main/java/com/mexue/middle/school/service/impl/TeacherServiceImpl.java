package com.mexue.middle.school.service.impl;

import com.mexue.middle.school.entity.Teacher;
import com.mexue.middle.school.mapper.TeacherMapper;
import com.mexue.middle.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    
    @Autowired
    private TeacherMapper teacherMapper;
    
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return teacherMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public int insert(Teacher record) {
        return teacherMapper.insert(record);
    }
    
    @Override
    public int insertSelective(Teacher record) {
        return teacherMapper.insertSelective(record);
    }
    
    @Override
    public Teacher selectByPrimaryKey(Integer id) {
        return teacherMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public int updateByPrimaryKeySelective(Teacher record) {
        return teacherMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(Teacher record) {
        return teacherMapper.updateByPrimaryKey(record);
    }
    
    @Override
    public List<Teacher> queryList(Teacher record) {
        System.out.println("查询列表");
        return Collections.emptyList();
    }
}
