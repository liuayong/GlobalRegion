package com.mexue.middle.school.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hspedu.json.jackson.PersonSerialize;
import lombok.Data;

import java.util.Date;

@Data
@JsonSerialize(using = PersonSerialize.class)
public class Person {
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
    // https://blog.csdn.net/dear_Alice_moon/article/details/103528795
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    
    
    //public String getCode() {
    //    return code;
    //}
    //
    //public void setCode(String code) {
    //    this.code = code == null ? null : code.trim();
    //}
    
    
}