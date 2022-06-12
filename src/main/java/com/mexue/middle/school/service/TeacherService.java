package com.mexue.middle.school.service;

import com.mexue.middle.school.entity.Teacher;

import java.util.List;

public interface TeacherService extends BaseService<Teacher> {
    
    List<Teacher> queryList(Teacher record);
    
}
