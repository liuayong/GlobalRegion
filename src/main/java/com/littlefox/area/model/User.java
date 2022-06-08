package com.littlefox.area.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private String passWord;
    private String comments;
    private String userName;
    private Integer userCode;
    private Date createTime;
}
