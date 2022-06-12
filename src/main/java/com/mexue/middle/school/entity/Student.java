package com.mexue.middle.school.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Integer id;

    private Integer schoolId;

    private String rosterNo;

    private String name;

    private Integer sex;

    private String nation;

    private String birth;

    private Byte active;

    private String headPhotoUrl;

    private String idPhotoUrl;

    private String idCard;

    private String nativePlace;

    private String address;

    private String politics;

    private String duty;

    private Date createTime;

    private Date updateTime;
}