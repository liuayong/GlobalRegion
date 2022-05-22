package com.littlefox.area.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * 传入参数的时候(下换线转为驼峰)
 * https://www.jianshu.com/p/55e6e0943d79
 * https://cloud.tencent.com/developer/article/1868921
 * https://www.cnblogs.com/newAndHui/p/14767675.html
 */
@Data
public class GetCarRequest implements Serializable {
    
    /**
     * 上家系统订单号，唯一、不可重复、不可空
     */
    @JsonProperty(value = "order_card")
    
    private String orderCard;
    
    /**
     * 卡号(唯一，不可重复)
     */
    private String cardId;
    
    @JsonProperty(value = "my_name")
    private String testName;
    
    
    @JsonProperty(value = "user_Name")
    private String userName;
    
    public int say(int a) {
        System.out.println("执行了say方法 一个参数");
        int rand = new Random().nextInt(100);
        return rand + a;
    }
    
    public int say(int a, Integer b) {
        System.out.println("执行了say方法 a,b两个参数");
        return a + b;
    }
    
    public void say() {
        System.out.println("执行了say方法 没有参数");
        
    }
    
    private String secret() {
        System.out.println("secret");
        int rand = new Random().nextInt(100);
        return "hello - " + rand;
    }
    
    //SuppressWarnings({"all"})
    public static void main(String[] args) throws Exception {
        say0();
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++");
        say1();
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++");
        say2();
    }
    
    public static void say2() throws Exception {
        
        String clzz = "com.littlefox.area.vo.GetCarRequest";
        Class<?> aClass = Class.forName(clzz);
        Object o = aClass.newInstance();    // 获取实例对象
        Integer i = 100;
        //System.out.printf("%s,%s ", i.getClass() == Integer.class, int.class == Integer.class);
        
        // java.lang.NoSuchMethodException: com.littlefox.area.vo.GetCarRequest.say(int, int)
        Method say = aClass.getMethod("say", int.class, Integer.class);
        System.out.println(say.getName());
        
        System.out.println("参数个数: " + say.getParameterCount());
        System.out.println("============== 调用部分 =====================");
        Object invoke = say.invoke(o, i, 100);
        System.out.println(invoke);
        
    }
    
    public static void say1() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        
        String clzz = "com.littlefox.area.vo.GetCarRequest";
        Class<?> aClass = Class.forName(clzz);
        Object o = aClass.newInstance();    // 获取实例对象
        Integer i = 100;
        System.out.printf("%s,%s ", i.getClass() == Integer.class, int.class == Integer.class);
        Method say = aClass.getMethod("say", int.class);
        System.out.println(say.getName());
        
        System.out.println("参数个数: " + say.getParameterCount());
        System.out.println("============== 调用部分 =====================");
        Object invoke = say.invoke(o, i);
        System.out.println(invoke);
        
    }
    
    public static void say0() {
        try {
            String clzz = "com.littlefox.area.vo.GetCarRequest";
            Class<?> aClass = Class.forName(clzz);
            Object o = aClass.newInstance();    // 获取实例对象
            Method say = aClass.getMethod("say");
            System.out.println(say.getName());
            
            System.out.println("参数个数: " + say.getParameterCount());
            System.out.println("============== 调用部分 =====================");
            Object invoke = say.invoke(o);
            System.out.println(invoke);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    
}
