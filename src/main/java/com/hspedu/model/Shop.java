package com.hspedu.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Shop implements Serializable {
    private String name;
    private String category;
    private Double price;
    private Integer number;
    private Date createTime;
}
