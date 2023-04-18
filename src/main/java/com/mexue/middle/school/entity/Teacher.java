package com.mexue.middle.school.entity;

import com.mexue.middle.school.util.DateUtil;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

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


    public static Teacher getOneTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(111);
        teacher.setName("刘阿勇");
        teacher.setCreateTime(new Date());
        teacher.setUpdateTime(DateUtil.strToDate("2022-2-3"));
        return  teacher;
    }
    
}