package com.hspedu.java8;

public interface Supplier3<T> {
    T get();
}

class Car {
    //Supplier3是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier3<Car> supplier) {
        return supplier.get();
    }
    
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
    
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }
    
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}