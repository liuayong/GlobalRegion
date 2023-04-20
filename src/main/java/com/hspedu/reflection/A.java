package com.hspedu.reflection;


import lombok.Data;

@Data
public class A {
    public String hobby22;


    private   String job = "A job";

    private Integer innerAofAge = 10 ;

    private Integer anInt ;

    public void hi() {

    }

    public A() {
    }

    public A(String name) {
    }

    @Override
    public String toString() {
        return "A{" +
                "hobby22='" + hobby22 + '\'' +
                ", job='" + job + '\'' +
                ", innerAofAge=" + innerAofAge +
                ", anInt=" + anInt +
                '}';
    }
}
