package com.mexue.middle.school.mapper;

import com.littlefox.area.Application;
import com.mexue.middle.school.entity.Student;
import com.mexue.middle.school.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StudentMapperTest {
    @Autowired
    private TeacherMapper teacherMapper;
    
    @Resource
    private StudentMapper studentMapper;
    
    @Resource
    private TeacherService teacherService;
    
    @Test
    public void selectByPrimaryKey() {
        Integer id = 9;
        Student student = studentMapper.selectByPrimaryKey(id);
        System.out.println("student = " + student);
        
        
    }
    
    @Test
    public void selectByRecord() {
        Integer id = 9;
        
        Student studentSearch = new Student();
        studentSearch.setId(id);
        studentSearch.setSchoolId(60);
        
        Student student1 = studentMapper.selectByRecord(studentSearch);
        System.out.println("student1 = " + student1);
        
    }
    
    
    @Test
    public void insertSelective() {
        Student student = new Student();
        student.setName("留洋");
        student.setBirth("2022年6月12日");
        student.setCreateTime(new Date(System.currentTimeMillis() - 3600 * 1000 * 3));
        student.setNativePlace("中国湖南");
        
        int i = studentMapper.insertSelective(student);
        System.out.println("i = " + i);
        System.out.println(student.getId());
    }
}