package com.hspedu.java8;

import org.junit.Test;

public class Lambda {
    
    @Test
    public void test2() {
        Runnable runnable = () -> System.out.println("lambda表达式");
        Thread thread = new Thread(runnable);
        thread.start();
    }
    
    @Test
    public void test1() {
        Lambda tester = new Lambda();
        
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
        
        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);
        
        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);
        
        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
        
        // 那么就可以使用Lambda表达式来表示该接口的一个实现(注：JAVA 8 之前一般是用匿名类实现的)：
        GreetingService greetingService3 = new GreetingService() {
            @Override
            public void sayMessage(String message) {
                System.out.println(message + ": 使用匿名类实现");
            }
        };
        greetingService3.sayMessage("阿勇");
        
    }
    
    interface MathOperation {
        int operation(int a, int b);
    }
    
    interface GreetingService {
        void sayMessage(String message);
        
        default int sayMessage2(String message, int a) {
            return a + 10;
        }
        
    }
    
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
    
    private void operate(int a, int b, GreetingService mathOperation) {
        //return mathOperation.operation(a, b);
    }
}
