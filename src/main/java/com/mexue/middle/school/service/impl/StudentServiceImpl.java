package com.mexue.middle.school.service.impl;

import com.mexue.middle.school.entity.Student;
import com.mexue.middle.school.mapper.StudentMapper;
import com.mexue.middle.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
    private StudentMapper studentMapper;
    
    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
        this.baseMapper = studentMapper;
    }
}
