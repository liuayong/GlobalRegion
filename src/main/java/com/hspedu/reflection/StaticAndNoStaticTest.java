package com.hspedu.reflection;

public class StaticAndNoStaticTest {
    public static void staticMethod(String[] args) {
        for (String str : args) {
            System.out.println(str);
        }
    }

    public String NoStaticMethod(String[] args) {
        for (String str : args) {
            System.out.println(str);
        }

        return Thread.currentThread().getStackTrace()[1].getMethodName();
    }

    private static String staticPrivateMethod(String s) {
        System.out.println("this is a private static method and the parameters is: " + s);
        return Thread.currentThread().getStackTrace()[1].getMethodName();
    }

    private void noStaticPrivateMethod(String s, Integer i) {
        System.out.println("this is a private no static method and the parameters is: " + s + i);
    }
}
