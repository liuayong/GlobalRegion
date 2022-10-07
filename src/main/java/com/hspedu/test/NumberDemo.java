package com.hspedu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)
// @SpringBootTest
public class NumberDemo {

    @Test
    public void doubleToInt() {
        double doubleValue = 82.14; // 82.14
        //typecase double to int
        int intValue = (int) doubleValue; // 82
        System.out.println("intValue1: " + intValue);
    }

    @Test
    public void doubleToInt2() {
        Double doubleValue = 82.14; // 82.14
        //typecase double to int
        int intValue = doubleValue.intValue(); // 82
        System.out.println("intValue2: " + intValue);
    }

    @Test
    public void doubleToInt3() {
        Object doubleValue = 82.14; // 82.14
        System.out.println(doubleValue.getClass() == Double.class );
        System.out.println(doubleValue instanceof  Double);
        //typecase double to int
        int intValue = ((Double)doubleValue).intValue(); // 82
        System.out.println("intValue3: " + intValue);
    }



}
