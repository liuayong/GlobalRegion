package com.hspedu.class_;

import com.hspedu.Cat;

public class Class01 {
    
    public static void main(String[] args) throws Exception {
        //看看Class类图
        //1. Class也是类，因此也继承Object类
        //Class
        //2. Class类对象不是new出来的，而是系统创建的
        //(1) 传统new对象
        /*  ClassLoader类
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return loadClass(name, false);
            }
         */
        System.out.println(Class.class.getName());
        Cat cat = new Cat();
        
        /*
            ClassLoader 类, 仍然是通过 ClassLoader 类加载 Cat 类的 Class 对象
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return loadClass(name, false);
            }
        */
        Class cls1 = Class.forName("com.hspedu.Cat");
        //3. 对于某个类的 Class 类对象，在内存中只有一份，因为类只加载一次
        Class cls2 = Class.forName("com.hspedu.Cat");
        System.out.println(cls1.hashCode());
        System.out.println(cls2.hashCode());
        Class cls3 = Class.forName("com.hspedu.Dog");
        System.out.println(cls3.hashCode());
    }
}
