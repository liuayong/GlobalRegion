package com.hspedu.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/8
 **/
@Data
public class Student {
    private String name;
    private int age;
    private float grade;
    
    public static List<Student> getList() {
        Student p1 = new Student();
        p1.setAge(10);
        p1.setName("HAI p1");
        p1.setGrade(98);
        
        Student p2 = new Student();
        p2.setAge(30);
        p2.setName("Banana p2");
        p2.setGrade(70);
        
        Student p3 = new Student();
        p3.setAge(20);
        p3.setName("Apple p3");
        p3.setGrade(83);
        
        Student p4 = new Student();
        p4.setAge(15);
        p4.setName("good p4");
        p4.setGrade(80);
        
        Student[] list = {p1, p2, p3, p4};
        
        return new ArrayList<>(Arrays.asList(list));
    }
}
