package com.mexue.middle.school.service.impl;

import com.mexue.middle.school.entity.Teacher;
import com.mexue.middle.school.mapper.TeacherMapper;
import com.mexue.middle.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService {
    
    private TeacherMapper teacherMapper;
    
    //public TeacherServiceImpl(TeacherMapper teacherMapper) {
    //    System.out.println("有参构造方法");
    //
    //    this.teacherMapper = teacherMapper;
    //}
    //
    //public TeacherServiceImpl() {
    //    System.out.println("无参构造方法");
    //    System.out.println(teacherMapper);
    //}
    
    @Autowired
    public void setTeacherMapper(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
        this.baseMapper = teacherMapper;    // 基类接受子类的赋值
    }
    
    @Override
    public List<Teacher> queryList(Teacher record) {
        System.out.println("查询列表");
        return Collections.emptyList();
    }
}
