package com.mexue.middle.school.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Teacher {
    private Integer id;
    
    private Integer schoolId;
    
    private String name;
    
    private Integer sex;
    
    private String mobile;
    
    private String headPhotoUrl;
    
    private Date createTime;
    
    private Date updateTime;
    
}