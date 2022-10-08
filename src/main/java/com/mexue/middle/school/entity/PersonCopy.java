package com.mexue.middle.school.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hspedu.json.jackson.PersonSerialize;
import com.mexue.middle.school.util.BeanUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PersonCopy {
    private Long id;
    
    private String code;
    
    private String name;
    
    private Byte sex;
    
    private Short age;
    
    private String political;
    
    private String origin;
    
    private String professional;
    
    
    private String phone;
    
    // 序列化与反序列化时需要做特殊处理  https://blog.csdn.net/belalds/article/details/80355198
    //@DateTimeFormat(pattern="yyyy/MM/dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    
    
    //public String getCode() {
    //    return code;
    //}
    //
    //public void setCode(String code) {
    //    this.code = code == null ? null : code.trim();
    //}
    
    
    public static PersonCopy convert(Person person) {
        return BeanUtil.convert(person, PersonCopy.class);
    }
    
    public String toString2() {
        return "PersonCopy{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", political='" + political + '\'' +
                ", origin='" + origin + '\'' +
                ", professional='" + professional + '\'' +
                ", phone='" + phone + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}