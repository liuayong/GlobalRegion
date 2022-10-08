package com.hspedu.test;

public class CpuDemo {
    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("i = " + i);
    }
}
