package com.hspedu.util;

import com.byd.tool.PrintUtil;
import com.hspedu.reflection.Person;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射的 Utils 函数集合
 * 提供访问私有变量, 获取泛型类型 Class, 提取集合中元素属性等 Utils 函数
 */
@Slf4j
public class ReflectionUtils {


    /**
     * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
     *
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        Object result = null;

        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            log.error("getFieldValue:", e);
        }

        return result;
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     *
     * @param object
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            log.error("setFieldValue:", e);
        }
    }


    /**
     * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     *
     * @param clazz
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }

        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class) params[index];
    }

    /**
     * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     *
     * @param <T>
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperGenericType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     *
     * @param object
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {

        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                //Method 不在当前类定义, 继续向上转型
            }
        }

        return null;
    }

    /**
     * 使 filed 变为可访问
     *
     * @param field
     */
    public static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param object
     * @param filedName
     * @return
     */
    public static Field getDeclaredField(Object object, String filedName) {

        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(filedName);
            } catch (NoSuchFieldException e) {
                //Field 不在当前类定义, 继续向上转型
            }
        }
        return null;
    }

    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected)
     *
     * @param object
     * @param methodName
     * @param parameterTypes
     * @param parameters
     * @return
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     */
    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes,
                                      Object[] parameters) throws InvocationTargetException {

        Method method = getDeclaredMethod(object, methodName, parameterTypes);

        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
        }

        method.setAccessible(true);

        try {
            return method.invoke(object, parameters);
        } catch (IllegalAccessException e) {
            log.error("invokeMethod:", e);
        }

        return null;
    }

    /**
     * https://blog.csdn.net/HaHa_Sir/article/details/124559273
     *
     * @param clzz
     * @param methodName 方法名称
     * @param argsType   参数类型
     * @return boolean
     * @Description: 判断是否包含某个方法
     * @version v1.0
     * @author wu
     * @date 2022年5月3日 下午5:50:13
     */
    public static boolean hasMethod(Class clzz, String methodName, Class[] argsType) {
        // 从当前类查找
        try {
            Method declaredMethod = clzz.getDeclaredMethod(methodName, argsType);
            if (declaredMethod != null) {
                return true;
            }
        } catch (NoSuchMethodException e) {
        }

        // 从父类中查找
        try {
            Method method = clzz.getMethod(methodName, argsType);
            if (null != method) {
                return true;
            }
        } catch (NoSuchMethodException e) {

        }
        return false;
    }

    /**
     * @param clzz
     * @param methodName
     * @param argsType
     * @return boolean
     * @Description: 判断类中是否包含某个方法
     * @version v1.0
     * @author wu
     * @date 2022年5月2日 下午11:03:56
     * @see org.springframework.util.ReflectionUtils
     */
    public static boolean hasMethod2(Class clzz, String methodName, Class[] argsType) {
        Method method = org.springframework.util.ReflectionUtils.findMethod(clzz, methodName, argsType);
        if (null != method) {
            return true;
        }
        return false;
    }

    /**
     * 获取一个对象的 简单类名
     *
     * @param o
     * @return
     */
    public static String getClassSimpleName(Object o) {
        return o != null ? o.getClass().getSimpleName() : null;
    }

    /**
     * 获取一个类的属于属性
     * 获取某个对象的所有属性，包含父类， 但不包含 java.lang.Object
     *
     * @param searchType
     * @return
     */
    public static List<Field> getAllFields(Class<?> searchType) {
        List<Field> allFileds = new ArrayList<>();
        while (searchType != Object.class) {
            Field[] declaredFields = searchType.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                allFileds.add(declaredField);
            }
            searchType = searchType.getSuperclass();
        }
        return allFileds;
    }

    /**
     * https://blog.csdn.net/qq_32452623/article/details/126295843 反射黑魔法
     * https://blog.51cto.com/u_15327002/3756836
     * 获取一个类的属于属性
     * 获取某个对象的所有属性，包含父类， 但不包含 java.lang.Object
     *
     * @param searchType
     * @return
     */
    public static List<String> getAllFieldNames(Class<?> searchType) {
        List<String> allFileds = new ArrayList<>();
        while (searchType != Object.class) {
            Field[] declaredFields = searchType.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                String fieldName = declaredField.getName();
                allFileds.add(fieldName);
            }
            searchType = searchType.getSuperclass();
        }
        return allFileds;
    }


    /**
     * 获取父类的所有属性字段
     *
     * @param searchType
     * @return
     */
    public static List<Field> getSuperFields(Class<?> searchType) {
        List<Field> superFileds = new ArrayList<>();
        do {
            searchType = searchType.getSuperclass();
            Field[] declaredFields = searchType.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                superFileds.add(declaredField);
            }
            searchType = searchType.getSuperclass();
        } while (searchType != Object.class);

        return superFileds;
    }


    /**
     * 判断某个属性在父类中是否存在
     *
     * @param searchType
     * @param filedName
     * @return
     */
    public static boolean superFieldExists(Class<?> searchType, String filedName) {
        List<Field> superFields = getSuperFields(searchType);
        for (Field superField : superFields) {
            if (superField.getName().equals(filedName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取父类某个属性的 值
     *
     * @param o
     * @param filedName
     * @return
     */
    public static Object getSuperFieldValue(Object o, String filedName) {
        Class<?> searchType = o.getClass();
        List<Field> superFields = getSuperFields(searchType);
        for (Field superField : superFields) {
            if (superField.getName().equals(filedName)) {
                superField.setAccessible(true);
                try {
                    return superField.get(o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    log.error("e = {}", e);
                }
            }
        }
        return null;
    }

}