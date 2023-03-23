package com.hspedu.reflection;

import lombok.Data;

@Deprecated
@Data
public class Person extends A implements IA, IB {
    //属性
    public String perName;
    protected static int perAge; // 4 + 8 = 12
    String job = "Person job";
    private double sal;

    //构造器
    public Person() {
    }

    public Person(String perName) {
    }

    //私有的
    private Person(String perName, int perAge) {

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
}
