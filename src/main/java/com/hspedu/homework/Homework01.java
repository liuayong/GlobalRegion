package com.hspedu.homework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author
 * @version 1.0
 */
public class Homework01 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        /**
         * 定义PrivateTest类，有私有name属性，并且属性值为hellokitty
         * 提供getName的公有方法
         * 创建PrivateTest的类，利用Class类得到私有的name属性，修改私有的name属性值，并调用getName()的方法打印name属性值
         */
        //1. 得到 PrivateTest类对应的Class对象
        
        Class<PrivateTest> cls = PrivateTest.class;
        PrivateTest privateTest = cls.newInstance();
        
        Class<?> aClass = Homework01.class.getClassLoader().loadClass("com.hspedu.homework.PrivateTest");
        System.out.println(aClass == cls);
        Object o = aClass.newInstance();
        Field nameField = aClass.getDeclaredField("name");
        nameField.setAccessible(true);
        int modifiers = nameField.getModifiers();
        System.out.println(modifiers);
        
        Object fieldVal = nameField.get(o);
        System.out.printf("获取成员的值: %s, 类型=%s\n", fieldVal, fieldVal.getClass().getName());
        nameField.set(o, "刘阿勇");
        
        fieldVal = nameField.get(o);
        System.out.printf("获取成员的值: %s, 类型=%s\n", fieldVal, fieldVal.getClass().getName());
        
    }
}

class PrivateTest {
    private String name = "hellokitty";
    
    //默认无参构造器
    public String getName() {
        return name;
    }
}

