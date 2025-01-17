package com.mexue.middle.school.service;

import com.littlefox.area.Application;
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


@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootTest(classes = Application.class)
public class TeacherServiceTest extends TestCase {
    
    @Resource
    private TeacherService teacherService;
    
    @Test
    public void selectByPrimaryKey() {
        Integer id = 153;
        Teacher teacher = teacherService.selectByPrimaryKey(id);
        System.out.println("teacher = " + teacher);
    }
    
    @Test
    public void updateByPrimaryKeySelective() {
        Integer id = 153;
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setCreateTime(new Date());
        teacher.setMobile("13651291261");
        teacherService.updateByPrimaryKeySelective(teacher);
    }
    
    @Test
    public void queryList() {
        Teacher teacher = new Teacher();
        List<Teacher> teachers = teacherService.queryList(teacher);
    }
}