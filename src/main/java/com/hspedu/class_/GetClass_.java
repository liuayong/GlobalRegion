package com.hspedu.class_;

import com.hspedu.Car;
import com.hspedu.Dog;

/**
 * 演示得到Class对象的各种方式
 */
public class GetClass_ {
    public static void main(String[] args) throws ClassNotFoundException {
        //1. Class.forName
        String classAllPath = "com.hspedu.Car"; //通过读取配置文件获取
        Class<?> cls1 = Class.forName(classAllPath);
        System.out.println(cls1);
        
        //2. 类名.class , 应用场景: 用于参数传递
        Class<Car> cls2 = Car.class;
        System.out.println(cls1 == cls2);
        
        //3. 对象.getClass(), 应用场景，有对象实例
        Car car = new Car();
        Class<? extends Car> cls3 = car.getClass();
        System.out.println(cls3);
        System.out.println(cls1 == cls3);
        
        //4. 通过类加载器【4种】来获取到类的Class对象
        //(1)先得到类加载器 car
        ClassLoader classLoader = car.getClass().getClassLoader();
        ClassLoader classLoader2 = (new Dog()).getClass().getClassLoader();
        ClassLoader classLoader3 = Dog.class.getClassLoader();
        System.out.printf("类加载器%s,%s\n", classLoader == classLoader2, classLoader == classLoader3);
        
        //(2)通过类加载器得到Class对象
        Class cls4 = classLoader.loadClass(classAllPath);
        System.out.println(cls4);
        System.out.println(cls1 == cls4);
        
        System.out.println("===================================================");
        //5. 基本数据(int, char,boolean,float,double,byte,long,short) 按如下方式得到Class类对象
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;
        Class<Boolean> booleanClass = boolean.class;
        System.out.println(integerClass);//int
        
        //6. 基本数据类型对应的包装类，可以通过 .TYPE 得到Class类对象
        Class<Integer> type1 = Integer.TYPE;
        Class<Character> type2 = Character.TYPE; //其它包装类BOOLEAN, DOUBLE, LONG,BYTE等待
        System.out.println(type1);
        
        System.out.printf("基本数据类型与包装数据类型%s,%s, %s\n"
                , integerClass.hashCode(), type1.hashCode(), integerClass.hashCode() == type1.hashCode());//?
    }
}
