// Package name 'com.hspedu.reflection.question' does not correspond to the file path 'com.hspedu.reflection'
package com.hspedu.reflection;

import com.hspedu.Cat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection02 {
    
    public static final int times = 100;
    public static String clazz = "com.hspedu.Cat";  // Cat.class.getName()
    
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //m1();   // 1143
        //m2();   // 1106
        //m3();   // 1140
        
        
        // 方法对象， 非单例的，
        Class cls = Class.forName("com.hspedu.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi2");
        System.out.println(hi);
        System.out.println(hi.getName());
        Method hi2 = cls.getMethod("hi2");
        System.out.println(hi2);
        System.out.println(hi2.getName());
        System.out.println(hi == hi2);  // false
        
    }
    
    //传统方法来调用 hi
    public static void m1() {
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            cat.hi2();
        }
        long end = System.currentTimeMillis();
        System.out.println("m1() 耗时=" + (end - start));
    }
    
    
    //反射机制调用方法
    public static void m2() throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        Class cls = Class.forName("com.hspedu.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi2");
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            hi.invoke(o);//反射调用方法
        }
        long end = System.currentTimeMillis();
        System.out.println("m2() 耗时=" + (end - start));
    }
    
    //反射调用优化 + 关闭访问检查
    public static void m3() throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        Class cls = Class.forName("com.hspedu.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi2");
        hi.setAccessible(true);//在反射调用方法时，取消访问检查
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            hi.invoke(o);//反射调用方法
        }
        long end = System.currentTimeMillis();
        System.out.println("m3() 耗时=" + (end - start));
    }
}
