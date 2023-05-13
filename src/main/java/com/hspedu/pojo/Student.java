package com.hspedu.pojo;

import lombok.Data;

import java.io.Serializable;
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
public class Student implements Serializable {
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
        
        List<Student> students = new ArrayList<>(Arrays.asList(list));
        students.add(new Student("曾国藩", 20, 83.0F));
        students.add(new Student("王阳明", 20, 99.0F));
        students.add(new Student("李鸿章", 10, 98.0F));
        return students;
    }
    
    
    public Student() {
    }
    
    public Student(String name, int age, float grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}
