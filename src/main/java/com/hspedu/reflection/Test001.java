package com.hspedu.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Test001 {
    
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        //1. 得到 Student 类对应的 Class 对象
        Class<?> clzz = Class.forName("com.hspedu.reflection.Student");
        Class<?> stuClass = Test001.class.getClassLoader().loadClass("com.hspedu.reflection.Student");
        System.out.println(clzz == stuClass);
        
        //2. 创建对象
        Object o = stuClass.newInstance();//o 的运行类型就是 Student
        System.out.println(o.getClass());//Student
        Object fieldVal;
        
        Field ageField = stuClass.getField("age");
        fieldVal = ageField.get(o);
        System.out.printf("获取成员的值: %s, 类型=%s\n", fieldVal, fieldVal.getClass().getName());
        ageField.set(o, 33);
        fieldVal = ageField.get(o);
        System.out.printf("获取成员的值: %s, 类型=%s\n", fieldVal, fieldVal.getClass().getName());
        
        System.out.println("==============获取静态属性==================");
        Field nameField = stuClass.getDeclaredField("name");
        nameField.setAccessible(true);
        fieldVal = nameField.get(null);
        System.out.printf("获取静态成员的值: %s, 类型=%s\n", fieldVal, fieldVal == null ? "NULL" : fieldVal.getClass().getName());
        nameField.set(null, "静态成员变量数据");
        fieldVal = nameField.get(null);
        System.out.printf("获取成员的值: %s, 类型=%s\n", fieldVal, fieldVal.getClass().getName());
    
    
    }
    
    private static void method01() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<User> userClass = User.class;
        Constructor<?> constructor1 = userClass.getDeclaredConstructor(int.class, String.class);
        System.out.println(constructor1.getName());
        constructor1.setAccessible(true);
        Object obj = constructor1.newInstance(11, "你好");
        System.out.println(obj);
    }
}
