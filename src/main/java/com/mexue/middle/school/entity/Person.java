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
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public Byte getSex() {
        return sex;
    }
    
    public void setSex(Byte sex) {
        this.sex = sex;
    }
    
    public Short getAge() {
        return age;
    }
    
    public void setAge(Short age) {
        this.age = age;
    }
    
    public String getPolitical() {
        return political;
    }
    
    public void setPolitical(String political) {
        this.political = political == null ? null : political.trim();
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }
    
    public String getProfessional() {
        return professional;
    }
    
    public void setProfessional(String professional) {
        this.professional = professional == null ? null : professional.trim();
    }
}