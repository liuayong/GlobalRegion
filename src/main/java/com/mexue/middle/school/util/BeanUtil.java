package com.mexue.middle.school.util;

import com.byd.tool.PrintUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;


public class BeanUtil {


    private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 拷贝属性
     */
    public static <T> T copyProperties(Object source, T target) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (BeansException e) {
            log.error("copy properties error ", e);
            handleReflectionException(e);
        }
        return target;
    }

    /**
     * 拷贝属性,限制某些字段
     *
     * @param source
     * @param target
     */
    public static <T> T copyProperties(Object source, T target, String... specifiedFields) {
        if (source == null || target == null) {
            return null;
        }

        List<String> fieldList = specifiedFields != null ? Arrays.asList(specifiedFields) : null;
        if (fieldList == null || fieldList.isEmpty()) {
            copyProperties(source, target);
            return target;
        }

        Set<String> targetFields = getFileds(target);
        for (String field : fieldList) {

            Object propVal = getPropVal(source, field);
            if (propVal != null && targetFields.contains(field)) {
                setPropVal(target, field, propVal);
            }

        }
        return target;
    }


    /**
     * 获取对象的属性值
     *
     * @param entity
     * @param propName
     * @param <E>
     * @return
     */
    public static <E> Object getPropVal(E entity, String propName) {
        if (entity == null) {
            return null;
        }

        try {
            String firstLetter = propName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + propName.substring(1);
            Method method = entity.getClass().getMethod(getter);
            return method.invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用反射设置对象的属性值
     *
     * @param obj       需要設置值的对象
     * @param fieldName 需要設置值的属性
     * @param value     需要设置的值
     * @return 设置值后的对象
     */
    public static void setPropVal(Object obj, String fieldName, Object value) {
        try {
            String firstWord = fieldName.substring(0, 1).toUpperCase();
            String methodName = String.format("set%s%s", firstWord, fieldName.substring(1));
            Method method = obj.getClass().getMethod(methodName, value.getClass());
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取对象的属性
     *
     * @param bean
     * @return
     */
    public static Set<String> getFileds(Object bean) {

        List<String> beanFields = new ArrayList<>();
        Class<?> clazz = bean.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] field = clazz.getDeclaredFields();
            for (Field f : field) {
                f.setAccessible(true);
                beanFields.add(f.getName());
            }
        }

        return new HashSet<>(beanFields);
    }


    /**
     * 拷贝属性,忽略null， src值为null的属性忽略
     *
     * @param source
     * @param target
     */
    public static <T> T copyPropertiesIgnoreNull(Object source, T target) {
        try {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        } catch (BeansException e) {
            log.error("copy properties error ", e);
            handleReflectionException(e);
        }
        return target;
    }

    /**
     * 拷贝属性，值赋值target值为null的属性   target值为null的属性将被拷贝
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T copyNullProperties(Object source, T target) {
        try {
            // String[] nullPropertyNames = getNullPropertyNames(target);
            //获取目标对象的所有字段
            Field[] targetFields = target.getClass().getDeclaredFields();
            for (Field targetField : targetFields) {
                targetField.setAccessible(true);
                Object targetValue = targetField.get(target);
                String fieldName = targetField.getName();
                if (targetValue == null) {
                    Object fieldValue = com.hspedu.util.ReflectionUtils.getFieldValue(source, fieldName);
                    targetField.set(target, fieldValue);
                }
                targetField.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException error ", e);
        } catch (BeansException e) {
            log.error("copy properties error ", e);
            handleReflectionException(e);
        }
        return target;
    }


    /**
     * 拷贝属性,忽略某些字段
     *
     * @param source
     * @param target
     */
    public static <T> T copyPropertiesIgnoreFields(Object source, T target, String... ignoreProperties) {
        try {
            BeanUtils.copyProperties(source, target, ignoreProperties);
        } catch (BeansException e) {
            log.error("copy properties error ", e);
            handleReflectionException(e);
        }
        return target;
    }


    /**
     * 转换Bean对象
     */
    public static <T> T convert(Object source, Class<T> tClass) {
        T t = null;
        try {
            t = tClass.newInstance();
            if (source == null) {
                return t;
            }
            BeanUtils.copyProperties(source, t);
        } catch (Exception e) {
            log.error("tClass convert error ", e);
            handleReflectionException(e);
        }
        return t;
    }

    /**
     * 拷贝指定的属性
     *
     * @param source
     * @param tClass
     * @param specifiedFields
     * @param <T>
     * @return
     */
    public static <T> T convert(Object source, Class<T> tClass, String... specifiedFields) {
        T t = null;
        try {
            t = tClass.newInstance();
            if (source == null) {
                return t;
            }
            copyProperties(source, t, specifiedFields);
        } catch (Exception e) {
            log.error("tClass convert error ", e);
            handleReflectionException(e);
        }
        return t;
    }

    /**
     * 转换List对象
     */
    public static <T> List<T> convert(List<?> source, Class<T> tClass) {
        List<T> result = new ArrayList<T>();
        try {
            for (Object o : source) {
                if (o != null) {
                    T t = tClass.newInstance();
                    BeanUtils.copyProperties(o, t);
                    result.add(t);
                }
            }
        } catch (Exception e) {
            log.error("List convert error ", e);
            handleReflectionException(e);
        }
        return result;
    }


    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    public static String[] getNullPropertyNames2(@NonNull Object source) {
        Assert.notNull(source, "source object must not be null");
        // if (source == null) {
        //     return new String[0];
        // }

        List<String> allFieldNames = com.hspedu.util.ReflectionUtils.getAllFieldNames(source.getClass());
        PrintUtil.println(allFieldNames, 10);
        Set<String> emptyNames = new HashSet<String>();
        for (String fieldName : allFieldNames) {
            Object fieldValue = com.hspedu.util.ReflectionUtils.getFieldValue(source, fieldName);
            if (fieldValue == null) {
                emptyNames.add(fieldName);
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    private static void handleReflectionException(Exception e) {
        ReflectionUtils.handleReflectionException(e);
    }


    /**
     * 将JavaBean对象封装到Map集合当中
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map<String, Object> entityToMap(Object bean) {
        Map<String, Object> map = null;
        try {
            //创建Map集合对象
            map = new HashMap<String, Object>();
            //获取对象字节码信息,不要Object的属性
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            //获取bean对象中的所有属性
            PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : list) {
                String key = pd.getName();//获取属性名
                Object value = pd.getReadMethod().invoke(bean); //调用getter()方法,获取内容

                if (value != null) {
                    map.put(key, value);//增加到map集合当中
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
