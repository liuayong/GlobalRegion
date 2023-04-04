package com.hspedu.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * https://blog.csdn.net/riemann_/article/details/100829164
 */
public class ReflectionMethod {

    @Test
    public void staticMethod() throws Exception {
        // 调用静态方法,不需要获取类对象
        // 获取字节码对象
        Class<StaticAndNoStaticTest> clazz = (Class<StaticAndNoStaticTest>) Class.forName("com.hspedu.reflection.StaticAndNoStaticTest");
        System.out.println(clazz == StaticAndNoStaticTest.class);

        String[] s = new String[]{"riemann", "chow"};

        // 获取Method对象
        Method staticMethod = clazz.getMethod("staticMethod", String[].class);

        // 调用invoke方法来调用 1、调用静态方法，不需要获取类对象。
        Object invoke = staticMethod.invoke(null, (Object) s);
        System.out.println("\ninvoke = " + invoke);

    }


    @Test
    public void testNoStaticMethod() throws Exception {
        // 调用非静态方法，需要获取类对象
// 获取字节码对象
        Class<StaticAndNoStaticTest> clazz = (Class<StaticAndNoStaticTest>) Class.forName("com.hspedu.reflection.StaticAndNoStaticTest");
// 获取一个对象
        Constructor<StaticAndNoStaticTest> constructor = clazz.getConstructor();
        StaticAndNoStaticTest instance = constructor.newInstance();
        String[] s = new String[]{"riemann", "chow"};
// 获取Method对象
        Method noStaticMethod = clazz.getMethod("NoStaticMethod", String[].class);
// 调用invoke方法来调用
        Object invoke = noStaticMethod.invoke(instance, (Object) s);
        System.out.println("\ninvoke = " + invoke);

    }


    @Test
    public void staticPrivateMethod() throws Exception {

        Class<StaticAndNoStaticTest> clazzStaticPrivate = (Class<StaticAndNoStaticTest>) Class.forName("com.hspedu.reflection.StaticAndNoStaticTest");
        Constructor<StaticAndNoStaticTest> constructor2 = clazzStaticPrivate.getConstructor();
        StaticAndNoStaticTest instance2 = constructor2.newInstance();
        Method staticPrivateMethod = clazzStaticPrivate.getDeclaredMethod("staticPrivateMethod", new Class[]{String.class});
        // 强制进入
        staticPrivateMethod.setAccessible(true);
        Object invoke = staticPrivateMethod.invoke(instance2, "test static and private");
        System.out.println("\ninvoke = " + invoke);
    }

    @Test
    public void test2() throws Exception {
    }

}
