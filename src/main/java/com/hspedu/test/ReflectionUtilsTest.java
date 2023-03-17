package com.hspedu.test;

import com.hspedu.reflection.A;
import com.hspedu.reflection.IB;
import com.hspedu.reflection.Person;
import com.hspedu.util.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://blog.csdn.net/HaHa_Sir/article/details/124559273
 * 1、ReflectionUtils 工具类：位于 spring-core 包，完整包名是：org.springframework.util.ReflectionUtils 。
 * 反射的 Utils 函数集合
 * 提供访问私有变量, 获取泛型类型 Class, 提取集合中元素属性等 Utils 函数
 */
@Slf4j
public class ReflectionUtilsTest {

    @Test
    public void test1() {
        Person person = new Person();

        boolean m2 = ReflectionUtils.hasMethod(Person.class, "m2", null);
        System.out.println("m2 = " + m2);
        boolean hi = ReflectionUtils.hasMethod(Person.class, "hi", null);
        System.out.println("hi = " + hi);
        boolean hi2 = ReflectionUtils.hasMethod(Person.class, "hi2", null);
        System.out.println("hi2 = " + hi2);


        boolean m1 = ReflectionUtils.hasMethod(Person.class, "m1", new Class[]{String.class, int.class, double.class});
        System.out.println("m1 = " + m1);

        boolean m1_1 = ReflectionUtils.hasMethod(Person.class, "m1_1", new Class[]{String.class, Integer.class, Double.class});
        System.out.println("m1_1 = " + m1_1);


        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        m2 = ReflectionUtils.hasMethod2(Person.class, "m2", null);
        System.out.println("m2 = " + m2);
        hi = ReflectionUtils.hasMethod2(Person.class, "hi", null);
        System.out.println("hi = " + hi);
        hi2 = ReflectionUtils.hasMethod2(Person.class, "hi2", null);
        System.out.println("hi2 = " + hi2);


        m1 = ReflectionUtils.hasMethod2(Person.class, "m1", new Class[]{String.class, int.class, double.class});
        System.out.println("m1 = " + m1);

        m1_1 = ReflectionUtils.hasMethod2(Person.class, "m1_1", new Class[]{String.class, Integer.class, Double.class});
        System.out.println("m1_1 = " + m1_1);


        Method[] methods = Person.class.getMethods();
        for (Method method : methods) {
            System.out.println("本类以及父类的方法=" + method.getName());
        }
        Method[] declaredMethods = Person.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("本类中所有方法=" + declaredMethod.getName());
        }

    }

    @Test
    public void test2() {
        Person person = new Person();

        boolean m2 = ReflectionUtils.hasMethod(Person.class, "m2", null);
        System.out.println("m2 = " + m2);
        boolean hi = ReflectionUtils.hasMethod(Person.class, "hi", null);
        System.out.println("hi = " + hi);
        boolean hi2 = ReflectionUtils.hasMethod(Person.class, "hi2", null);
        System.out.println("hi2 = " + hi2);


        boolean m1 = ReflectionUtils.hasMethod(Person.class, "m1", new Class[]{String.class, int.class, double.class});
        System.out.println("m1 = " + m1);

        boolean m1_1 = ReflectionUtils.hasMethod(Person.class, "m1_1", new Class[]{String.class, Integer.class, Double.class});
        System.out.println("m1_1 = " + m1_1);

    }

    @Test
    public void test3() {
        try {
            Class clazz = Class.forName("java.util.ArrayList");
            Method method = clazz.getMethod("add", Object.class);
            System.out.println("Method Found");
        } catch (NoSuchMethodException e) {
            System.out.println("Method Not Found");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> list = new ArrayList<>();
        list.add("");

    }

    /**
     * https://cloud.tencent.com/developer/article/1065205
     * 判断一个类是否实现了某个接口
     */
    @Test
    public void test4() {
        Class<?>[] interfaces = Person.class.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println("接口信息=" + anInterface);
        }

        // IB.class
        System.out.println(Person.class.isAssignableFrom(IB.class));    // false
        System.out.println(Person.class.isAssignableFrom(A.class)); // false

        System.out.println(IB.class.isAssignableFrom(Person.class));    // true
        System.out.println(A.class.isAssignableFrom(Person.class)); // true

    }

    /**
     * http://www.bjpowernode.com/javazixun/10486.html
     */
    @Test
    public void test5() {
        Person person = new Person();

        System.out.println(Person.class instanceof Class);
        System.out.println(person instanceof Object);
        System.out.println(Person.class instanceof Object);
        System.out.println(person.getClass() instanceof Object);
    }


    /**
     * https://juejin.cn/post/6882161443819487246
     * http://www.bjpowernode.com/javazixun/10486.html
     */
    @Test
    public void test6_1() {
        Object i = new Integer(7);
        if (i instanceof Number) {
            System.out.println("Integer i is a Number");
        } else {
            System.out.println("Integer i isn't a Number");
        }
        if (i instanceof Serializable) {
            System.out.println("Integer i is a Serializable");
        } else {
            System.out.println("Integer i isn't a Serializable");
        }
        if (i instanceof Integer) {
            System.out.println("Integer i is an Integer");
        } else {
            System.out.println("Integer i isn't an Integer");
        }
        if (i instanceof Float) {
            System.out.println("Integer i is a Float");
        } else {
            System.out.println("Integer i isn't a Float");
        }
    }


    @Test
    public void test6_2() {
        Object i = new Integer(7);
        if (i.getClass().equals(Number.class)) {
            System.out.println("Integer i is a Number");
        } else {
            System.out.println("Integer i isn't a Number");
        }
        if (i.getClass().equals(Serializable.class)) {
            System.out.println("Integer i is a Serializable");
        } else {
            System.out.println("Integer i isn't a Serializable");
        }
        if (i.getClass().equals(Integer.class)) {
            System.out.println("Integer i is an Integer");
        } else {
            System.out.println("Integer i isn't an Integer");
        }
        if (i.getClass().equals(Float.class)) {
            System.out.println("Integer i is a Float");
        } else {
            System.out.println("Integer i isn't a Float");
        }
    }


}