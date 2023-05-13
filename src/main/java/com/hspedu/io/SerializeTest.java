package com.hspedu.io;

import com.byd.tool.PrintUtil;
import com.hspedu.pojo.Student;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/5/12
 **/
public class SerializeTest implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream os = new ObjectOutputStream(buffer)) {
            os.writeInt(12345);
            os.writeUTF("Hello");
            os.writeObject(Double.valueOf(123.456));
        }
        System.out.println(Arrays.toString(buffer.toByteArray()));
        
        ByteArrayInputStream bufferin = new ByteArrayInputStream(buffer.toByteArray());
        //反序列化,反序列化时，由JVM直接构造出Java对象，不调用构造方法，构造方法内部的代码，在反序列化时根本不可能执行
        try (ObjectInputStream in = new ObjectInputStream(bufferin)) {
            int n = in.readInt();
            String s = in.readUTF();
            Double d = (Double) (in.readObject());
            PrintUtil.println(n);
            PrintUtil.println(s);
            PrintUtil.println(d);
        }
    }
    
    @Test
    public void test2() throws IOException {
        
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(buffer)) {
            objectOutputStream.writeInt(50);
            objectOutputStream.writeUTF("String这个整个zg");
            Student student = new Student();
            student.setName("刘阿勇");
            student.setAge(18);
            objectOutputStream.writeObject(student);
            try {
                objectOutputStream.writeObject(Class.forName("com.hspedu.pojo.User"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Arrays.toString(buffer.toByteArray()));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.toByteArray());
        try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            System.out.println(objectInputStream.readInt());
            System.out.println(objectInputStream.readUTF());
            Object object;
            try {
                System.out.println(object = objectInputStream.readObject());
                String stuName = ((Student) object).getName();
                System.out.println("stuName = " + stuName);
                System.out.println(object = objectInputStream.readObject());
                try {
                    Method method = object.getClass().getMethod("getUserList");
                    try {
                        method.invoke(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void test1() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            // 写入int:
            output.writeInt(12345);
            // 写入String:
            output.writeUTF("Hello");
            // 写入Object:
            output.writeObject(Double.valueOf(123.456));
        }
        byte[] a = buffer.toByteArray();
        System.out.println(a.length);
        System.out.println(Arrays.toString(a));
    }
}
