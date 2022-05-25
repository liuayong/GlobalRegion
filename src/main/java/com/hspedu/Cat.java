package com.hspedu;

public class Cat {
    
    public String name = "tom";
    protected int age = 8;
    
    public Cat() {
    }
    
    public Cat(String name) {
        this.name = name;
    }
    
    public void hi() {
        System.out.println("hi 喵喵叫");
    }
    
    public String hi2() {
        String str = "";
        for (int i = 0; i < 1000; i++) {
            str += Math.random() + "\n";
            //double v = Math.floor(Math.random() * 1000);
        }
        return str;
    }
}
