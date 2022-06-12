package com.mexue.middle.school.service;

import com.littlefox.area.Application;
import com.mexue.middle.school.entity.Student;
import com.mexue.middle.school.entity.Teacher;
import com.mexue.middle.school.mapper.TeacherMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootTest(classes = Application.class)
public class StudentServiceTest {
    
    @Resource
    private StudentService studentService;
    
    @Test
    public void selectByPrimaryKey() {
        Integer id = 16;
        
        Student student = studentService.selectByPrimaryKey(id);
        System.out.println("student = " + student);
    }
    
    @Test
    public void updateByPrimaryKeySelective() {
        Integer id = 16;
        Student student = new Student();
        student.setRosterNo(new Random().nextInt(100000) + "");
        
        student.setName("我是刘阿勇");
        student.setId(id);
        
        studentService.updateByPrimaryKeySelective(student);
    }
    
    
}