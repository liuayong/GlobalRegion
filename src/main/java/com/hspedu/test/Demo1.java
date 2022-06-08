package com.hspedu.test;

public class Demo1 {
    public static void main(String[] args) {
        System.out.println(Super.a);
    
        Sub1 sub1 = new Sub1();
        System.out.println(Sub1.a);
        System.out.println(Super.a);
        
        System.out.println("-------------------------");
        
        Sub2 sub2 = new Sub2();
        System.out.println(Sub2.a);
        System.out.println(Super.a);
    }
}
