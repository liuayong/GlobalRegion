package com.hspedu.homework;


import com.hspedu.Car;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author
 * @version 1.0
 */
public class Homework02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        /**
         * 利用Class类的forName方法得到File类的class 对象
         * 在控制台打印File类的所有构造器
         * 通过 newInstance 的方法创建File对象，并创建 E:\mynew.txt 文件
         */
        //1. Class类的forName方法得到File类的class 对象
        String fullClass = "java.io.File";
        Class<?> cls = Class.forName(fullClass);
        
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        for (int i = 0; i < declaredConstructors.length; i++) {
            Constructor<?> c = declaredConstructors[i];
            System.out.print(c.getName());
            Class<?>[] parameterTypes = c.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                System.out.print("\t参数: ");
                System.out.print(parameterTypes[j].getName() + "  ");
            }
            System.out.println();
        }
        System.out.println("===============================================");
        Constructor<?> constructor1 = cls.getConstructor(String.class);
        Constructor<?> constructor2 = cls.getDeclaredConstructor(String.class);
        // 构造器对象不是单例的有两份，不会相等，
        System.out.printf("构造器: %s, %s, 是否相等=%s\n", constructor1, constructor2, constructor1 == constructor2);
        Object file = constructor1.newInstance("D:\\mynew.txt");
        Method method = cls.getMethod("createNewFile");
        Object invoke = method.invoke(file);
        System.out.println("invoke = " + invoke);
        
        
        //cls.getMethod("")
        boolean newFile = new File("D:\\mynew1.txt").createNewFile();
        System.out.println(newFile);
        
        Car car = new Car();
        Class<? extends Car> carClass = car.getClass();
        System.out.println(carClass);
        // car.getClass().getClassLoader();  // sun.misc.Launcher$AppClassLoader@18b4aac2
        Class<?> aClass = car.getClass().getClassLoader().loadClass("java.io.File");
        System.out.println(aClass == cls);  // Class java.io.File 只会有一份
        
        
      
    }
}
