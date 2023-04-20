package com.hspedu.reflection;

import lombok.Data;

@Data
public class PersonCopy extends A implements IA, IB {

    //属性
    public String perName;
    protected static int perAge; // 4 + 8 = 12
    String job;
    private Double sal;

    // private Integer innerAofAge;
    //
    // private Integer anInt;


    //构造器
    public PersonCopy() {
    }

    public PersonCopy(String perName) {
    }

    //私有的
    private PersonCopy(String perName, int perAge) {

    }

    //方法
    public void m1(String name, int age, double sal) {

    }

    protected String m2() {
        return null;
    }

    void m3() {

    }

    private void m4() {

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" +
                "perName='" + perName + '\'' +
                ", job='" + job + '\'' +
                ", sal=" + sal +
                ", hobby22='" + getHobby22() + '\'' +
                ", innerAofAge=" + getInnerAofAge() +
                ", anInt=" + getAnInt() +
                "} " + super.toString();
    }
}
