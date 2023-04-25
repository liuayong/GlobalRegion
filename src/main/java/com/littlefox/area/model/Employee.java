package com.littlefox.area.model;


import lombok.Data;

@Data
public class Employee {
    
    private String name;
    private Integer age;
    
    private Boolean sex;
    
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}