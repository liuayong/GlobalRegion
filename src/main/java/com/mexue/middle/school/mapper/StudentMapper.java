package com.mexue.middle.school.mapper;

import com.mexue.middle.school.entity.Student;

public interface StudentMapper extends BaseMapper<Student> {
    Student selectByRecord(Student record);
}