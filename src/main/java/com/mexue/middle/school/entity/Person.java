package com.mexue.middle.school.entity;

import lombok.Data;

@Data
public class Person {
    private Long id;
    
    private String code;
    
    private String name;
    
    private Byte sex;
    
    private Short age;
    
    private String political;
    
    private String origin;
    
    private String professional;
    
    //public String getCode() {
    //    return code;
    //}
    //
    //public void setCode(String code) {
    //    this.code = code == null ? null : code.trim();
    //}
    
    
}