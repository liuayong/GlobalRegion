package com.hspedu.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodReference2 {
    
    public static void main(String args[]){
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );
    
        //System.out.println(cars);
        //System.out.println(cars.size());
    
        cars.forEach( Car::collide );
        //
        ////特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach( Car::repair );
        ////特定对象的方法引用：它的语法是instance::method实例如下：
        //final Car police = Car.create( Car::new );
        //cars.forEach( police::follow );
    }
    
}
