package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {
    
    public void method1(String a) {
        System.out.println(a);
    }
    
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Demo demoObj = new Demo();// Object of Demo class
        Class cObj = demoObj.getClass();
        Class[] carr = new Class[1];
        carr[0] = String.class;// class reference to java.lang.String class stored In the array of type Class
        Method mObj = cObj.getMethod("method1", carr);
        
        mObj.invoke(demoObj, "123123");
    }
}


