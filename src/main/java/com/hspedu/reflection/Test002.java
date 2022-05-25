package com.hspedu.reflection;

import com.hspedu.Car;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test002 {
    public static void main(String[] args) throws Exception {
        String fullClass = "java.io.File";
        Class<?> cls1 = Class.forName(fullClass);
        
        // 构造器
        Constructor<?> constructor1 = cls1.getConstructor(String.class);
        Constructor<?> constructor2 = cls1.getDeclaredConstructor(String.class);
        // 构造器对象不是单例的有两份，不会相等，
        System.out.printf("构造器: %s, %s, 是否相等=%s\n", constructor1, constructor2, constructor1 == constructor2);
        
        
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
        
        
        Class<?> aClass = Car.class.getClassLoader().loadClass("java.io.File");
        System.out.println(aClass == cls1);  // Class java.io.File 只会有一份
    }
}
