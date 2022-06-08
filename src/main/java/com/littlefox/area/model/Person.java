package com.littlefox.area.model;

// <T1,T2>

import lombok.Data;

@Data
public class Person<D, E> {
    private String name ="personName";
}
