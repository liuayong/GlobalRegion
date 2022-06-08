package com.littlefox.area.convert;

import com.littlefox.area.model.User;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 标准Map转Bean实现
 */
public class StandardMap2BeanProcessorImpl implements StandardMap2BeanProcessor {
    
    /**
     * 输入字符串Map
     */
    private Map<Object, Object> inObjectMap;
    
    /**
     * 映射关系
     */
    private List<Mapping<Object, String>> mappings;
    
    /**
     * 输出Bean
     */
    private Object outBean;
    
    /**
     * 输出Bean类型
     */
    private Class<?> outBeanClass;
    
    
    public Map<Object, Object> getInObjectMap() {
        return inObjectMap;
    }
    
    public void setInObjectMap(Map<Object, Object> inObjectMap) {
        this.inObjectMap = inObjectMap;
    }
    
    public List<Mapping<Object, String>> getMappings() {
        return mappings;
    }
    
    public void setMappings(List<Mapping<Object, String>> mappings) {
        this.mappings = mappings;
    }
    
    public Object getOutBean() {
        return outBean;
    }
    
    public void setOutBean(Object outBean) {
        this.outBean = outBean;
    }
    
    public Class<?> getOutBeanClass() {
        return outBeanClass;
    }
    
    public void setOutBeanClass(Class<?> outBeanClass) {
        this.outBeanClass = outBeanClass;
    }
    
    @Override
    public void complete() {
        Object outBean = this.outBean;
        if (outBean == null) {
            try {
                outBean = outBeanClass.newInstance();
            } catch (InstantiationException e) {
                throw new Map2BeanException(e);
            } catch (IllegalAccessException e) {
                throw new Map2BeanException(e);
            }
        }
        
        if (outBean == null) {
            return;
        }
        
        if (mappings == null) {
            return;
        }
        
        Object finalOutBean = outBean;
        mappings.stream().forEach(mapping -> {
            Object source = mapping.getSource();
            Object sourceValue = this.getInObjectMap().get(source);
            
            String targetFieldName = mapping.getTarget();
            Field field = this.getField(targetFieldName, this.getOutBeanClass());
            
            if (field != null) {
                
                boolean changeFlag = false;
                if (!field.isAccessible()) {
                    changeFlag = true;
                    field.setAccessible(true);
                }
                
                // 给filed字段设置值
                this.setOutBeanPropertiesData(field, finalOutBean, sourceValue);
                
                if (changeFlag) {
                    field.setAccessible(false);
                }
            }
            
            
        });
        
        this.outBean = outBean;
    }
    
    /**
     * 设置输出对象属性信息
     *
     * @param field        字段
     * @param finalOutBean 输出对象信息
     * @param sourceValue  值
     */
    protected void setOutBeanPropertiesData(Field field, Object finalOutBean, Object sourceValue) {
        try {
            field.set(finalOutBean, sourceValue);
        } catch (IllegalAccessException e) {
            throw new Map2BeanException(e);
        }
    }
    
    private Field getField(String name, Class<?> clazz) {
        if (clazz == null || clazz.equals(Object.class)) {
            return null;
        }
        Field field = null;
        
        if (field == null) {
            try {
                field = clazz.getField(name);
            } catch (NoSuchFieldException e) {
                try {
                    field = clazz.getDeclaredField(name);
                } catch (NoSuchFieldException e1) {
                    field = this.getField(name, clazz.getSuperclass());
                }
            }
        }
        
        return field;
    }
    
    /**
     * map 转  bean对象
     *
     * @param data         数据
     * @param mappings     映射
     * @param outBeanClass 输出bean类型
     * @param <R>          返回类型
     * @return 结果bean
     */
    public static <R> R map2Bean(Map<? super Object, ? super Object> data, List<Mapping<Object, String>> mappings, Class<R> outBeanClass) {
        StandardMap2BeanProcessorImpl standardMap2BeanProcessor = new StandardMap2BeanProcessorImpl();
        standardMap2BeanProcessor.setOutBean(null);
        standardMap2BeanProcessor.setMappings(mappings);
        standardMap2BeanProcessor.setOutBeanClass(outBeanClass);
        standardMap2BeanProcessor.setInObjectMap(data);
        standardMap2BeanProcessor.complete();
        return (R) standardMap2BeanProcessor.getOutBean();
    }
    
    
    /**
     * list map 转 list bean对象
     *
     * @param data         数据
     * @param mappings     映射
     * @param outBeanClass 输出bean类型
     * @param <R>          返回类型
     * @return 结果bean 列表
     */
    public static <R> List<R> listMap2ListBean(List<Map<? super Object, ? super Object>> data, List<Mapping<Object, String>> mappings, Class<R> outBeanClass) {
        StandardMap2BeanProcessorImpl standardMap2BeanProcessor = new StandardMap2BeanProcessorImpl();
        List<R> result = new ArrayList<>();
        for (Map<? super Object, ? super Object> map : data) {
            standardMap2BeanProcessor.setOutBean(null);
            standardMap2BeanProcessor.setMappings(mappings);
            standardMap2BeanProcessor.setOutBeanClass(outBeanClass);
            standardMap2BeanProcessor.setInObjectMap(map);
            standardMap2BeanProcessor.complete();
            result.add((R) standardMap2BeanProcessor.getOutBean());
        }
        return result;
        
    }
    
    public static void main(String[] args) {
        
        User user = new User();
        Map<String, Object> keyValues = new HashMap<>();
        
        user.setPassWord("password");
        user.setComments("test method!");
        user.setUserName("wang shisheng");
        user.setUserCode(2018998770);
        
        Map<? super Object, ? super Object> map = new HashMap<>();
        map.put("passWord", "W$%^@#$@#%");
        map.put("userName", "刘阿勇");
        map.put("cmt", "转换注释");
        map.put("createTime", new Date());
        map.put("userCode", 32323);
    
    
        Mapping<Object, String> standardMapping1 = new StandardMappingImpl<>("passWord", "passWord");
        Mapping<Object, String> standardMapping2 = new StandardMappingImpl<>("cmt", "comments");
        List<Mapping<Object, String>> standardMappings = Arrays.asList(standardMapping1, standardMapping2);
    
        /*
        StandardMappingImpl standardMapping = new StandardMappingImpl("string", "string");
        List<Mapping<Object, String>> standardMappings = Arrays.asList(standardMapping);
        */
        
        System.out.println(standardMappings);
        
        //
        User demo2Bean = (User) StandardMap2BeanProcessorImpl.map2Bean(map, standardMappings, User.class);
        
        System.out.println(demo2Bean);
        
    }
}
