package com.hspedu.reflection ;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection01 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        String className = "com.hspedu.Cat";
        Class<?> cls = Class.forName(className);
        Constructor<?>[] constructors = cls.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            Constructor<?> cstr = constructors[i];
            System.out.printf("%s, %s\n", cstr.getName(), cstr.toString());
        }
        //java.lang.reflect.Constructor: 代表类的构造方法, Constructor 对象表示构造器
        Constructor constructor = cls.getConstructor(); //()中可以指定构造器参数类型, 返回无参构造器
        System.out.println(constructor);//Cat()
        
        
    }
    
    /**
     * 获取属性
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private static void getField() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        String className = "com.hspedu.Cat";
        Class<?> aClass = Class.forName(className);
        //System.out.println(aClass);
        Object o = aClass.newInstance();
        //System.out.println("o 的运行类型=" + o.getClass()); //运行类型
        
        
        // 获取字段属性
        //Field nameField = aClass.getField("name");
        //System.out.println(nameField.getName());
        //Class<?> declaringClass = nameField.getDeclaringClass();
        //Object o1 = nameField.get(o);
        //System.out.println(o1);
        //System.out.println(o1.getClass().getName());
        //System.out.println(declaringClass);
        
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            System.out.println(field.getName());
            int modifiers = field.getModifiers();
            System.out.println(modifiers);
            Object o1 = field.get(o);
            // can not access a member of class com.hspedu.Cat with modifiers "protected"
            System.out.println(o1);
        }
        System.out.println("-------------------------------------");
        // 不能获取私有属性字段
        Field field = aClass.getField("age"); // Field 'age' is not public, use getDeclaredFields
        System.out.println(field.getName());
        Class<?> declaringClass = field.getDeclaringClass();
        Object o1 = field.get(o);
        System.out.println(o1);
        System.out.println(o1.getClass().getName());
        System.out.println(declaringClass);
    }
    
    
    private static void invoke() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        String className = "com.hspedu.Cat";
        Class<?> aClass = Class.forName(className);
        System.out.println(aClass);
        Object o = aClass.newInstance();
        System.out.println("o 的运行类型=" + o.getClass()); //运行类型
        
        Method method = aClass.getMethod("hi");
        Object invoke = method.invoke(o);
        System.out.println("invoke = " + invoke);
    }
}
