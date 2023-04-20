package com.hspedu.reflection;

//import org.junit.jupiter.api.Test;

import com.byd.tool.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static com.hspedu.util.ReflectionUtils.getSuperFieldValue;
import static com.hspedu.util.ReflectionUtils.getSuperFields;

/**
 * @author
 * @version 1.0
 * 演示如何通过反射获取类的结构信息
 */
@Slf4j
public class ReflectUtil {
    public static void main(String[] args) {

    }

    @Test
    public void api_02() throws ClassNotFoundException, NoSuchMethodException {
        //得到Class对象
        Class<?> personCls = Class.forName("com.hspedu.reflection.Person");
        //getDeclaredFields:获取本类中所有属性
        //规定 说明: 默认修饰符 是0 ， public  是1 ，private 是 2 ，protected 是 4 , static 是 8 ，final 是 16
        Field[] declaredFields = personCls.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("本类中所有属性=" + declaredField.getName()
                    + " 该属性的修饰符值=" + declaredField.getModifiers()
                    + " 该属性的类型=" + declaredField.getType());
        }

        //getDeclaredMethods:获取本类中所有方法
        Method[] declaredMethods = personCls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("本类中所有方法=" + declaredMethod.getName()
                    + " 该方法的访问修饰符值=" + declaredMethod.getModifiers()
                    + " 该方法返回类型" + declaredMethod.getReturnType());

            //输出当前这个方法的形参数组情况
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("该方法的形参类型=" + parameterType);
            }
        }

        //getDeclaredConstructors:获取本类中所有构造器
        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("====================");
            System.out.println("本类中所有构造器=" + declaredConstructor.getName());//这里老师只是输出名

            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("该构造器的形参类型=" + parameterType);
            }


        }

    }

    //第一组方法API
    @Test
    public void api_01() throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        Object o = new Object();
        PrintUtil.println(o);
        Field[] fields1 = o.getClass().getFields();
        for (Field field : fields1) {//增强for
            System.out.println("Object 属性=" + field.getName());
        }
        System.out.println("+++++++++++++++++++++获取某个对象的所有属性，包含父类， 但不包含 java.lang.Object +++++++++++++++++++++++++");
        Class<?> searchType = Person.class;
        List<String> allFieldNames = com.hspedu.util.ReflectionUtils.getAllFieldNames(searchType);
        PrintUtil.println(allFieldNames, 10);

        Person person = new Person();
        // Field innerAofAge = searchType.getField("innerAofAge");
        // PrintUtil.println(innerAofAge);  // java.lang.NoSuchFieldException: innerAofAge

        List<Field> superFields = getSuperFields(Person.class);
        PrintUtil.println(superFields, 20);
        List<Field> allFields = com.hspedu.util.ReflectionUtils.getAllFields(searchType);
        PrintUtil.println(allFields, 20);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   ");
        for (Field field : allFields) {
            log.info("name = {}, modifiers = {}", field.getName(), field.getModifiers());
            if ("innerAofAge".equals(field.getName())) {
                field.setAccessible(true);
                Object o1 = field.get(person);
                log.info("fieldValue = {}, valueType = {}", o1, com.hspedu.util.ReflectionUtils.getClassSimpleName(o1));
            }

            if ("job".equals(field.getName())) {
                field.setAccessible(true);
                log.info("获取某个属性值 job = {}", person.getJob());
                log.info("通过反射获取某个属性值 job = {}", field.get(person));

                // 先判断父类的属性值是否存在？
                log.info("通过反射获取父类的某个属性值 job1 = {}, job2 = {} ",
                        field.get(person), getSuperFieldValue(person, field.getName()));
                System.out.println("------------------------------------------------------------------------------");
            }
        }


        //得到Class对象
        Class<?> personCls = Class.forName("com.hspedu.reflection.Person");
        //getName:获取全类名
        System.out.println(personCls.getName());//com.hspedu.reflection.Person
        //getSimpleName:获取简单类名
        System.out.println(personCls.getSimpleName());//Person


        //getFields:获取所有public修饰的属性，包含本类以及父类的
        Field[] fields = personCls.getFields();
        for (Field field : fields) {//增强for
            System.out.println("本类以及父类的属性=" + field.getName());
        }
        //getDeclaredFields:获取本类中所有属性
        Field[] declaredFields = personCls.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("本类中所有属性=" + declaredField.getName());
        }
        //getMethods:获取所有public修饰的方法，包含本类以及父类的
        Method[] methods = personCls.getMethods();
        for (Method method : methods) {
            System.out.println("本类以及父类的方法=" + method.getName());
        }
        //getDeclaredMethods:获取本类中所有方法
        Method[] declaredMethods = personCls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("本类中所有方法=" + declaredMethod.getName());
        }
        //getConstructors: 获取所有public修饰的构造器，包含本类
        Constructor<?>[] constructors = personCls.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("本类的构造器=" + constructor.getName());
        }
        //getDeclaredConstructors:获取本类中所有构造器
        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("本类中所有构造器=" + declaredConstructor.getName());//这里老师只是输出名
        }


        //getPackage:以Package形式返回 包信息
        System.out.println(personCls.getPackage());//com.hspedu.reflection
        //getSuperClass:以Class形式返回父类信息
        Class<?> superclass = personCls.getSuperclass();
        System.out.println("父类的class对象=" + superclass);//
        //getInterfaces:以Class[]形式返回接口信息
        Class<?>[] interfaces = personCls.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println("接口信息=" + anInterface);
        }
        //getAnnotations:以Annotation[] 形式返回注解信息
        Annotation[] annotations = personCls.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("注解信息=" + annotation);//注解
        }


    }
}

