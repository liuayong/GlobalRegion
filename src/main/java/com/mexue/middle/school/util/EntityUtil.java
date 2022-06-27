package com.mexue.middle.school.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 实体Bean操作类，需结合java ,IdAndValue.java使用
 *
 * @author lindujia
 */
public class EntityUtil {
    public enum Direction {
        ASC("ASC", "升序"),
        DESC("DESC", "降序");
        private String code;
        private String desc;

        private Direction(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return this.code;
        }

        public String getDesc() {
            return this.desc;
        }

        public static Direction fromCode(String code) {
            Direction[] values = values();
            for (int i = 0; i < values.length; ++i) {
                if (code.equals(values[i].code)) {
                    return values[i];
                }
            }
            return null;
        }

        public static Direction fromCodeOrDesc(String code) {
            Direction[] values = values();
            for (int i = 0; i < values.length; ++i) {
                if (code.equals(values[i].code) || code.equals(values[i].desc)) {
                    return values[i];
                }
            }
            return null;
        }
    }

    /**
     * 特殊分隔符
     */
    public static final String SPECIAL_SPLIT = "Ξ";

    /**
     * 递归获取树结构数据
     *
     * @param level
     * @param listData
     * @param parentValue
     * @param <E>
     * @return
     */
    public static <E> Map<String, Object> listToTree(int level, List<E> listData, Object parentValue, String[] propertyArr, String[] sortArr, boolean isHumpKey) {
        if (propertyArr.length < 3) {
            return null;
        }
        Map<String, List<Map<String, Object>>> temp = new HashMap<String, List<Map<String, Object>>>();
        Map<String, List<E>> tempE = new HashMap<String, List<E>>();
        Long minParentId = null;
        String parentIdProperty = propertyArr[0];
        String idProperty = propertyArr[1];
        String textProperty = propertyArr[2];
        if (listData.size() > 0) {
            for (int i = 0; i < listData.size(); i++) {
                E oneData = listData.get(i);
                try {
                    Object parentId = parentIdProperty == null ? 0 : getTer(oneData, parentIdProperty);
                    if (null == parentValue && (parentId instanceof Integer || parentId instanceof Long)) {
                        if (null == minParentId || Long.valueOf(parentId.toString()).longValue() < minParentId.longValue()) {
                            minParentId = Long.valueOf(parentId.toString());
                        }
                    }
                    List<E> parentList = tempE.get(parentId + "");

                    if (null == parentList) {
                        parentList = new ArrayList<E>();
                    }
                    parentList.add(oneData);
                    tempE.put(parentId + "", parentList);
                } catch (Exception e) {
                }
            }
        }
        for (String key : tempE.keySet()) {
            List<E> oneList = tempE.get(key);
            if (null != sortArr && sortArr.length > 0) {
                listSort(oneList, sortArr);
            }
            List<Map<String, Object>> tempList = listToMapList(oneList, propertyArr);
            temp.put(key, tempList);
        }

        Map<String, Object> out = makeTree(level, temp, null == parentValue ? minParentId : parentValue, idProperty, isHumpKey);
        out.put(isHumpKey ? "idKey" : "id-Key", idProperty);
        out.put(isHumpKey ? "valueKey" : "value-Key", textProperty);
        return out;
    }

    private static Map<String, Object> makeTree(int level, Map<String, List<Map<String, Object>>> data, Object parentValue, String idProperty, boolean isHumpKey) {
        Map outMap = new HashMap();
        int nowLevel = level;
        String parentValueStr = String.valueOf(parentValue == null ? "" : parentValue);
        List<Map<String, Object>> outList = data.get(parentValueStr);
        if (null != outList && outList.size() > 0) {
            for (Map oneOut : outList) {
                oneOut.put(isHumpKey ? "levelPoint" : "level-point", level);
                if (!equal(parentValueStr, oneOut.get(idProperty))) {
                    Map oneChildren = makeTree(level + 1, data, oneOut.get(idProperty) + "", idProperty, isHumpKey);
                    List<Map<String, Object>> childrenList = (List<Map<String, Object>>) oneChildren.get(isHumpKey ? "childList" : "child-list");
                    if (null != childrenList && childrenList.size() > 0) {
                        oneOut.put(isHumpKey ? "childList" : "child-list", childrenList);
                        int childrenLevel = (Integer) oneChildren.get(isHumpKey ? "levelLength" : "level-length");
                        if (childrenLevel > nowLevel) {
                            nowLevel = childrenLevel;
                        }
                    }
                }
            }
        }
        outMap.put(isHumpKey ? "levelLength" : "level-length", nowLevel);
        outMap.put(isHumpKey ? "childList" : "child-list", outList);
        return outMap;
    }

    public static <E> Map<String, Object> listToTree(List<E> listData, Object parentValue, String[] propertyArr, String[] sortArr, boolean isHumpKey) {
        if (null != propertyArr[0]) {
            listSort(listData, propertyArr[0], propertyArr[1]);
        }
        Map outMap = listToTree(1, listData, parentValue, propertyArr, sortArr, isHumpKey);
        return outMap;
    }

    public static <E> Map<String, Object> listToTree(List<E> listData, String[] propertyArr, String[] sortArr, boolean isHumpKey) {
        Map outMap = listToTree(listData, null, propertyArr, sortArr, isHumpKey);
        return outMap;
    }

    public static <E> Map<String, Object> listToTree(List<E> listData, String[] propertyArr, boolean isHumpKey) {
        Map outMap = listToTree(listData, null, propertyArr, null, isHumpKey);
        return outMap;
    }

    public static <E> Map<String, Object> listToTree(List<E> listData, Object parentValue, String[] propertyArr, String[] sortArr) {
        if (null != propertyArr[0]) {
            listSort(listData, propertyArr[0], propertyArr[1]);
        }
        Map outMap = listToTree(1, listData, parentValue, propertyArr, sortArr, false);
        return outMap;
    }

    public static <E> Map<String, Object> listToTree(List<E> listData, String[] propertyArr, String[] sortArr) {
        Map outMap = listToTree(listData, null, propertyArr, sortArr);
        return outMap;
    }

    public static <E> Map<String, Object> listToTree(List<E> listData, Object parentValue, String parentIdProperty, String idProperty, String textProperty) {
        if (null != parentIdProperty) {
            listSort(listData, parentIdProperty, idProperty);
        }
        Map outMap = listToTree(listData, parentValue, new String[]{parentIdProperty, idProperty, textProperty}, null);
        return outMap;
    }

    public static <E> Map<String, Object> listToTree(List<E> listData, String idProperty, String textProperty) {
        Map outMap = listToTree(listData, null, null, idProperty, textProperty);
        return outMap;
    }

    public static <E> Map<String, Object> listToTree(List<E> listData, String... propertyArr) {
        if (null != propertyArr[0]) {
            listSort(listData, propertyArr[0], propertyArr[1]);
        }
        Map outMap = listToTree(1, listData, null, propertyArr, null, false);
        return outMap;
    }

    public static <E> Map<String, Object> listToTree(List<E> listData, boolean isHumpKey, String... propertyArr) {
        if (null != propertyArr[0]) {
            listSort(listData, propertyArr[0], propertyArr[1]);
        }
        Map outMap = listToTree(1, listData, null, propertyArr, null, isHumpKey);
        return outMap;
    }

    public static <E> List<E> mapToList(Map<String, E> map) {
        List<E> outList = new ArrayList<E>();
        if (null != map && map.size() > 0) {
            for (String key : map.keySet()) {
                outList.add(map.get(key));
            }
        }
        return outList;
    }

    public static <E> String mapToStr(Map<String, E> map) {
        List<E> outList = new ArrayList<E>();
        if (null != map && map.size() > 0) {
            for (String key : map.keySet()) {
                outList.add(map.get(key));
            }
        }
        return listToStr(outList);
    }


    public static boolean isNumber(Object obj) {
        if (null == obj) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^-{0,1}[0-9]+\\.{0,1}[0-9]*$");
            String value = String.valueOf(obj);
            if (pattern.matcher(value).matches()) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static <E> void listSort(List<E> listData, Direction direction, String... sortProperty) {
        Collections.sort(listData, new Comparator<E>() {
            public int compare(E o1, E o2) {
                try {
                    if (null == sortProperty || sortProperty.length <= 0) {
                        return comparison(o1, o2, direction);
                    } else {
                        Integer comparisonValue = 0;
                        for (String one : sortProperty) {
                            comparisonValue = comparison(getTer(o1, one), getTer(o2, one), direction);
                            if (!equal(comparisonValue, 0)) {
                                break;
                            }
                        }
                        return comparisonValue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
    }

    public static <E> void listSort(List<E> listData, String... sortProperty) {
        listSort(listData, Direction.ASC, sortProperty);
    }

    public static <E> String listToStrAndSort(List<E> list, String splitChar) {
        if (null != list && list.size() > 0) {
            Set<E> setTemp = new HashSet<E>();
            for (E one : list) {
                setTemp.add(one);
            }
            return listToStrAndSort(setTemp, splitChar);
        }
        return null;
    }

    public static <E> String listToStrAndSort(Set<E> set, String splitChar) {
        if (null != set && set.size() > 0) {
            List<E> listTemp = new ArrayList<>();
            for (E one : set) {
                listTemp.add(one);
            }
            listSort(listTemp);
            return listToStr(listTemp, splitChar);
        }
        return null;
    }

    public static <E> String listToStr(List<E> listTemp, String splitChar, String property) {
        StringBuffer outStr = new StringBuffer("");
        if (null != listTemp && listTemp.size() > 0) {
            for (E one : listTemp) {
                Object appendValue = one;
                if (!isBaseClass(one) && null != property) {
                    try {
                        appendValue = getTer(one, property);
                    } catch (Exception e) {
                        appendValue = null;
                    }
                }
                if (!isEmpty(appendValue)) {
                    if (outStr.length() > 0) {
                        outStr.append(splitChar);
                    }
                    outStr.append(appendValue);
                }
            }
        }
        return outStr.length() > 0 ? outStr.toString() : null;
    }

    // 提取属性转stringList [Created by 2019-07-19 11:04 DJ]
    public static <E> List<String> listToStrList(List<E> listTemp, String property) {
        List<String> outList = new ArrayList<>();
        if (null != listTemp && listTemp.size() > 0) {
            for (E one : listTemp) {
                Object appendValue = one;
                if (!isBaseClass(one) && null != property) {
                    try {
                        appendValue = getTer(one, property);
                    } catch (Exception e) {
                        appendValue = null;
                    }
                }
                if (!isEmpty(appendValue)) {
                    outList.add(baseClassToStr(appendValue));
                }
            }
        }
        return outList.size() > 0 ? outList : outList;
    }

    public static <E> String listToStr(List<E> listTemp, String propertyOrSplitChar) {
        String outStr = "";
        if (null != listTemp && listTemp.size() > 0) {
            if (isBaseClass(listTemp.get(0))) {
                outStr = listToStr(listTemp, propertyOrSplitChar, null);
            } else {
                outStr = listToStr(listTemp, ",", propertyOrSplitChar);
            }
        }
        return outStr;
    }

    public static <E> String listToStr(LinkedHashSet<E> set) {
        if (null != set && set.size() > 0) {
            List<E> listTemp = new ArrayList<>();
            listTemp.addAll(set);
            return listToStr(listTemp);
        }
        return null;
    }

    public static <E> String listToStr(List<E> listTemp) {
        String outStr = null ;
        if (null != listTemp && listTemp.size() > 0) {
            if (isBaseClass(listTemp.get(0))) {
                outStr = listToStr(listTemp, ",");
            } else {
                outStr = listToStr(listTemp, ",", "id");
            }
        }
        return outStr ;
    }


    public static <E> String listToStrAndSort(List<E> list) {
        return listToStrAndSort(list, ",");
    }

    public static <E> String listToStrAndSort(Set<E> set) {
        return listToStrAndSort(set, ",");
    }

    public static <E> String strSort(String strs, String splitChar) {
        if (null == strs) {
            return null;
        }
        String[] arrStr = strs.split(splitChar);
        List<String> arrList = Arrays.asList(arrStr);
        return listToStrAndSort(arrList, splitChar);
    }

    public static <E> String strSort(String strs) {
        return strSort(strs, ",");
    }

    public static <E> List<E> strToList(String strs, String splitChar, Class<E> e) {
        List<E> outList = new ArrayList<E>();
        if (null != strs && !isEmpty(strs) && null != splitChar && null != e) {
            String[] strsArr = strs.split(splitChar);
            if (Integer.class.getSimpleName().equals(e.getSimpleName())) {
                for (String one : strsArr) {
                    if (!isEmpty(one)) {
                        outList.add((E) Integer.valueOf(one));
                    }
                }
            } else if (Double.class.getSimpleName().equals(e.getSimpleName())) {
                for (String one : strsArr) {
                    if (!isEmpty(one)) {
                        outList.add((E) Double.valueOf(one));
                    }
                }
            } else if (Long.class.getSimpleName().equals(e.getSimpleName())) {
                for (String one : strsArr) {
                    if (!isEmpty(one)) {
                        outList.add((E) Long.valueOf(one));
                    }
                }
            } else if (String.class.getSimpleName().equals(e.getSimpleName())) {
                for (String one : strsArr) {
                    outList.add((E) one);
                }
            }
        }
        return outList;
    }

    public static List<Long> strToList(String strs) {
        return strToList(strs, ",", Long.class);
    }

    public static <E> Set<E> strToSet(String strs, String splitChar, Class<E> e) {
        Set<E> outSet = new HashSet<E>();
        if (null != strs && null != splitChar && null != e) {
            String[] strsArr = strs.split(splitChar);
            if (Integer.class.getSimpleName().equals(e.getSimpleName())) {
                for (String one : strsArr) {
                    outSet.add((E) Integer.valueOf(one));
                }
            } else if (Double.class.getSimpleName().equals(e.getSimpleName())) {
                for (String one : strsArr) {
                    outSet.add((E) Double.valueOf(one));
                }
            } else if (Long.class.getSimpleName().equals(e.getSimpleName())) {
                for (String one : strsArr) {
                    outSet.add((E) Long.valueOf(one));
                }
            } else if (String.class.getSimpleName().equals(e.getSimpleName())) {
                for (String one : strsArr) {
                    outSet.add((E) one);
                }
            }
        }
        return outSet;
    }

    public static Set<Long> strToSet(String strs) {
        return strToSet(strs, ",", Long.class);
    }

    public static Set<String> strToStrSet(String strs) {
        return strToSet(strs, ",", String.class);
    }

    public static <E> Map<String, E> listToMap(List<E> list, String... propertys) {
        if (null == propertys) {
            propertys = new String[]{"id"};
        }
        Map<String, E> outMap = new HashMap<>();
        if (null != list && list.size() > 0) {
            for (E one : list) {
                try {
                    String key = null;
                    if (one instanceof Long || one instanceof Double || one instanceof Integer || one instanceof String) {
                        key = one + "";
                    } else if (one instanceof Date) {
                        key = DateUtil.dateToStrSS((Date) one);
                    } else {
                        StringBuffer keyBuffer = new StringBuffer();
                        for (String property : propertys) {
                            if (keyBuffer.length() > 0) {
                                keyBuffer.append(",");
                            }
                            Object object = getTer(one, property);
                            if (object instanceof Date) {
                                keyBuffer.append(DateUtil.dateToStrSS((Date) object));
                            } else {
                                keyBuffer.append(String.valueOf(object));
                            }
                        }
                        key = keyBuffer.toString();
                    }
                    outMap.put(key, one);
                } catch (Exception e) {
                }
            }
        }
        return outMap;
    }

    public static <E> void listClearSame(List<E> list, String... propertys) {
        Map<String, E> map = listToMap(list, propertys);
        list = mapToList(map);
    }

    public static <E> Map<String, List<E>> listToListMap(List<E> list, String... propertys) {
        if (null == propertys) {
            propertys = new String[]{"id"};
        }
        Map<String, List<E>> outMap = new HashMap<>();
        if (null != list && list.size() > 0) {
            for (E one : list) {
                try {
                    String key = null;
                    if (one instanceof Long || one instanceof Double || one instanceof Integer || one instanceof String) {
                        key = one + "";
                    } else if (one instanceof Date) {
                        key = DateUtil.dateToStrSS((Date) one);
                    } else {
                        StringBuffer keyBuffer = new StringBuffer();
                        for (String property : propertys) {
                            if (keyBuffer.length() > 0) {
                                keyBuffer.append(",");
                            }
                            Object object = getTer(one, property);
                            if (object instanceof Date) {
                                keyBuffer.append(DateUtil.dateToStrSS((Date) object));
                            } else {
                                keyBuffer.append(String.valueOf(object));
                            }
                        }
                        key = keyBuffer.toString();
                    }
                    List<E> oneList = outMap.get(key);
                    if (null == oneList) {
                        oneList = new ArrayList<>();
                    }
                    oneList.add(one);
                    outMap.put(key, oneList);
                } catch (Exception e) {
                }
            }
        }
        return outMap;
    }

    public static <E> Map<String, E> listToMap(List<E> list) {
        return listToMap(list, null);
    }

    public static Map<String, String> strToMap(String str, String dataSplit) {
        if (null == dataSplit) {
            dataSplit = ",";
        }
        Map<String, String> outMap = new HashMap<>();
        if (!Validator.isEmpty(str)) {
            for (String one : str.split(dataSplit)) {
                outMap.put(one, one);
            }
        }
        return outMap;
    }


    public static Map<String, String> strToMap(String strs) {
        return strToMap(strs, null);
    }

//    public static boolean inStrs(String strs, String inStr) {
//        Map<String, String> map = strToMap(strs, null);
//        return !isEmpty(map.get(inStr));
//    }
//
//    public static String removeStrs(String strs, String inStr) {
//        Map<String, String> map = strToMap(strs, null);
//        map.remove(inStr);
//        List<String> out = mapToList(map);
//        return mapToStr(map);
//    }

    public static boolean equal(Object from, Object to) {
        boolean outFlag = false;
        if (null != from && null != to) {
            String fromStr = equalStr(from);
            String toStr = equalStr(to);
            outFlag = null != fromStr && null != toStr && fromStr.equals(toStr);
        }
        return outFlag;
    }

    private static String equalStr(Object obj) {
        String objStr = null;
        if (obj instanceof Long || obj instanceof Double || obj instanceof Integer || obj instanceof String) {
            objStr = obj + "";
        } else if (obj instanceof Date) {
            objStr = DateUtil.dateToStrSS((Date) obj);
        } else {
            try {
                Object objObject = getTer(obj, "id");
                if (null != objObject) {
                    objStr = String.valueOf(objObject);
                }
            } catch (Exception e) {
            }
        }
        return objStr;
    }


    /**
     * 判断对象的属性是否存在
     *
     * @param bean
     * @return
     */
    public static boolean isPropExists(Object bean, String propName) {

        if (propName == null || propName.length() <= 0) {
            return false;
        }

        boolean hasExists = false;

        Class<?> clazz = bean.getClass();
        outer:
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] field = clazz.getDeclaredFields();

            for (Field f : field) {
                f.setAccessible(true);

                if (propName.equals(f.getName())) {
                    hasExists = true;
                    break outer;
                }
            }
        }
        return hasExists;
    }

    /**
     * 检查对象是否为空，对象为空和对象属性都是空值都是空
     *
     * @param entity
     * @return
     */
    public static <E> boolean isEmpty(E entity) {

        if (null == entity) {
            return true;
        } else if (String.valueOf(entity).trim().equals("")) {
            return true;
        } else {
            Integer size = null;
            try {
                size = getSize(entity);
                if (size != null && size > 0) {
                    return false;
                }
            } catch (Exception e) {
            }

            if (!isBaseClass(entity)) {
                List<Field> list = new ArrayList<Field>();
                list.addAll(Arrays.asList(entity.getClass().getDeclaredFields()));
                list.addAll(Arrays.asList(entity.getClass().getSuperclass().getDeclaredFields()));
                for (Field field : list) {
                    if (!isEmpty(entity, field.getName())) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 调用SETTER方法（支持"属性.属性.属性"方式）【循环】
     *
     * @param <E>
     * @param entity   对象实例
     * @param property 属性名称
     * @param value    值
     */
    public static <E, V> void setTer(E entity, String property, V value) throws Exception {
        if (null == entity || isEmpty(property) || null == value) {
            return;
        }
        Object entityTemp = entity;
        String[] arrProperty = property.trim().split("\\.");
        for (int i = 0; i < arrProperty.length - 1; i++) {
            Object newTemp = invokeReturn(entityTemp, "get" + toUpperFirst(arrProperty[i]));
            if (null == newTemp) {
                newTemp = newEntity(entityTemp, arrProperty[i]);
                invokeNoReturn(entityTemp, "set" + toUpperFirst(arrProperty[i]), newTemp);
            }
            entityTemp = newTemp;
        }
        invokeNoReturn(entityTemp, "set" + toUpperFirst(arrProperty[arrProperty.length - 1]), value);
    }

    /**
     * 调用GETTER方法（支持"属性.属性.属性"方式）【循环】
     *
     * @param <E>
     * @param entity   对象实体
     * @param property 属性名称
     * @return 调用结果
     */
    public static <E> Object getTer(E entity, String property) throws Exception {
        if (null == entity || isEmpty(property)) {
            return null;
        }
        Object entityTemp = entity;
        for (String propertyArr : property.trim().split("\\.")) {
            entityTemp = invokeReturn(entityTemp, "get" + toUpperFirst(propertyArr));
            if (null == entityTemp) {
                break;
            }
        }
        return entityTemp;
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
            return "";
        }

        try {
            String firstLetter = propName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + propName.substring(1);
            Method method = entity.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(entity, new Object[]{});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 检测对象属性是否为空
     *
     * @param <E>
     * @param entity
     * @param property
     * @return
     */
    public static <E> boolean isEmpty(E entity, String property) {
        Object value = null;
        Integer size = null;
        try {
            value = getTer(entity, property);
        } catch (Exception e) {
        }
        try {
            size = getSize(value);
        } catch (Exception e) {
        }

        if ((null != value && String.valueOf(value).trim() != "") || (null != size && size > 0)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取集合类的大小
     *
     * @param <E>
     * @param entity
     * @return
     */
    public static <E> Integer getSize(E entity) throws Exception {
        Object size = invokeReturn(entity, "size");
        return null == size ? null : Integer.parseInt(size.toString());
    }

    /**
     * 传入Map key是对象属性名称，value是对象属性为空的时候输出的字符串
     *
     * @param <E>
     * @param entity
     * @param propertyMap
     * @return
     */
    public static <E> String propertyNullCheck(E entity, Map<String, String> propertyMap) throws Exception {
        for (String propertyName : propertyMap.keySet()) {
            if (isEmpty(getTer(entity, propertyName))) {
                return propertyMap.get(propertyName);
            }
        }
        return null;
    }

    /**
     * 根据Map规则映射对象的值（根据Key从to取出值设置到on的Value里）
     *
     * @param <T>
     * @param <O>
     * @param to
     * @param on
     * @param mapPN
     */
    public static <T, O> void mapToOther(T to, O on, Map<String, String> mapPN) throws Exception {
        if (isEmpty(to) || isEmpty(on)) {
            return;
        }
        for (String onPropertyName : mapPN.keySet()) {
            setTer(on, mapPN.get(onPropertyName), getTer(to, onPropertyName));
        }
    }

    /**
     * 把一个对象的非空属性映射到另一个对象去
     *
     * @param <T>
     * @param from
     * @param to
     */
    public static <T> void mapToOther(T from, T to) {
        if (isEmpty(from) || null == to) {
            return;
        }
        List<Field> list = allField(from);
        for (Field field : list) {
            if (!isEmpty(from, field.getName())) {
                try {
                    setTer(to, field.getName(), getTer(from, field.getName()));
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * 检测class类型 [Created by 2018-11-13 20:19 fxLDJ]
     *
     * @param entity
     * @param property
     * @param <E>
     * @return
     */
    public static <E> Class checkPropertyClass(E entity, String property) {
        List<Field> list = allField(entity);
        for (Field field : list) {
            if (field.getName().equals(property)) {
                return field.getType();
            }
        }
        return null;
    }

    /**
     * 检测class类型 [Created by 2018-11-13 20:19 fxLDJ]
     *
     * @param entity
     * @param methodName
     * @param <E>
     * @return
     */
    public static <E> Class checkMethodNameClass(E entity, String methodName) {

        char[] methodNameChar = methodName.toCharArray();
        int propertyPoint = 0;
        for (int i = 0; i < methodNameChar.length; i++) {
            String one = String.valueOf(methodNameChar[i]);
            if (one.toUpperCase().equals(one)) {
                propertyPoint = i;
                break;
            }
        }
        String property = toLowerFirst(methodName.substring(propertyPoint));
        List<Field> list = allField(entity);
        for (Field field : list) {
            if (field.getName().equals(property)) {
                return field.getType();
            }
        }
        return null;
    }

    /**
     * 拷贝一个对象，非应用
     *
     * @param <T>
     * @param from
     */
    public static <T> T copy(T from) {
        if (null == from) {
            return null;
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(from);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            return (T) ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 去除List集合里的空值，null，空格,0,0.0都为空值
     *
     * @param <E>
     * @param list
     */
    public static <E> void removeNullList(List<E> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            if (isEmpty(list.get(i))) {
                list.remove(i);
                i--;
            }
        }
    }

    //接口精简字段工具开始================================================

    /**
     * 实体对象按照属性转map实体
     *
     * @param e
     * @param showPropertyArray
     * @param decimalNum
     * @param datePattern
     * @param <E>
     * @return
     */
    public static <E> Map<String, Object> entityToMap(E e, String[] showPropertyArray, Integer decimalNum, String datePattern) {
        Map<String, Object> oneAdd = new HashMap();

        if (null == e) {
            return oneAdd;
        }
        SimpleDateFormat format = null == datePattern ? null : new SimpleDateFormat(datePattern);

        List<String> showPropertyList = new ArrayList<>();
        if (showPropertyArray == null || showPropertyArray.length <= 0) {
            List<Field> list = allField(e);
            for (Field field : list) {
                showPropertyList.add(field.getName());
            }
        } else {
            showPropertyList = Arrays.asList(showPropertyArray);
        }

        for (String property : showPropertyList) {
            Object value = null;
            try {
                value = getTer(e, property);
                if (!isEmpty(value)) {
                    if (isBaseClass(value)) {
                        value = formatValue(value, decimalNum, datePattern);
                    } else if (isList(value)) {
                        value = listToBOMList((List) value, decimalNum, datePattern);
                    } else if (isSet(value)) {
                        List listValue = new ArrayList();
                        listValue.addAll((Set) value);
                        value = listToBOMList(listValue, decimalNum, datePattern);
                    } else if (!isMap(value)) {
                        value = entityToMap(value);
                    }

                    String mapKey = property;
                    if (property.trim().split("\\.").length > 0) {
                        String tempMapKey = "";
                        String[] propertyArr = property.trim().split("\\.");
                        for (int i = propertyArr.length - 1; i >= 0; i--) {
                            if (tempMapKey.length() <= 0) {
                                tempMapKey = propertyArr[i];
                            } else {
                                tempMapKey = propertyArr[i] + toUpperFirst(tempMapKey);
                            }
                            if (!showPropertyList.contains(tempMapKey) && null == oneAdd.get(tempMapKey)) {
                                break;
                            }
                        }
                        mapKey = tempMapKey;
                    }
                    oneAdd.put(mapKey, value);

                }

            } catch (Exception ex) {
            }
        }
        return oneAdd;
    }

    /**
     * 查询条件对象按照属性转map实体
     *
     * @param e
     * @param <E>
     * @return
     */
    public static <E> Map<String, Object> searchToMap(E e) {
        Map<String, Object> oneAdd = new HashMap();
        if (null == e) {
            return oneAdd;
        }
        List<String> showPropertyList = new ArrayList<>();
        List<Field> list = allField(e);
        for (Field field : list) {
            showPropertyList.add(field.getName());
        }
        for (String property : showPropertyList) {
            Object value = null;
            try {
                value = getTer(e, property);
                if (!isEmpty(value)) {
                    String mapKey = property;
                    if (property.trim().split("\\.").length > 0) {
                        String tempMapKey = "";
                        String[] propertyArr = property.trim().split("\\.");
                        for (int i = propertyArr.length - 1; i >= 0; i--) {
                            if (tempMapKey.length() <= 0) {
                                tempMapKey = propertyArr[i];
                            } else {
                                tempMapKey = propertyArr[i] + toUpperFirst(tempMapKey);
                            }
                            if (!showPropertyList.contains(tempMapKey) && null == oneAdd.get(tempMapKey)) {
                                break;
                            }
                        }
                        mapKey = tempMapKey;
                    }
                    oneAdd.put(mapKey, value);
                }
            } catch (Exception ex) {
            }
        }
        return oneAdd;
    }

    /**
     * 实体对象按照属性转map实体
     *
     * @param e
     * @param showPropertyArray
     * @param <E>
     * @return
     */
    public static <E> Map<String, Object> entityToMap(E e, String... showPropertyArray) {
        return entityToMap(e, showPropertyArray, null, null);
    }

    public static <E> Map<String, Object> entityToMap(E e) {
        return entityToMap(e, null, null, null);
    }

    public static <E> void entityToMap(Map<String, Object> map, E e, String[] showPropertyArray) {
        Map<String, Object> mapNew = entityToMap(e, showPropertyArray, null, null);
        if (null != mapNew && mapNew.size() > 0) {
            for (String key : mapNew.keySet()) {
                map.put(key, mapNew.get(key));
            }
        }
    }


    /**
     * 实体对象列表按照属性转map列表
     *
     * @param list
     * @param showPropertyArray
     * @param decimalNum
     * @param datePattern
     * @param <E>
     * @return
     */
    public static <E> List<Map<String, Object>> listToMapList(List<E> list, String[] showPropertyArray, Integer decimalNum, String datePattern) {
        List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
        if (null == list || list.size() <= 0) {
            return outList;
        }
        for (E e : list) {
            outList.add(entityToMap(e, showPropertyArray, decimalNum, datePattern));
        }
        return outList;
    }

    /**
     * 实体对象列表按照属性转map列表
     *
     * @param list
     * @param showPropertyArray
     * @param <E>
     * @return
     */
    public static <E> List<Map<String, Object>> listToMapList(List<E> list, String... showPropertyArray) {
        return listToMapList(list, showPropertyArray, null, null);
    }

    public static <E> List<Map<String, Object>> listToMapList(List<E> list) {
        return listToMapList(list, null, null, null);
    }

    /**
     * 分页对象根据参数获取到map集合
     *
     * @param page
     * @param showPropertyArray
     * @param decimalNum
     * @param datePattern
     * @param <P>
     * @param <E>
     * @return
     */
    public static <P, E> Map<String, Object> pageToMap(P page, String[] showPropertyArray, Integer decimalNum, String datePattern) {
        Map<String, Object> out = new HashMap<String, Object>();
        List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
        try {
            Object objList = getTer(page, "items");
            List<E> list = (List<E>) objList;
            outList = listToMapList(list, showPropertyArray, decimalNum, datePattern);
            Integer pageCount = (Integer) getTer(page, "pageCount");
            Integer curPage = (Integer) getTer(page, "curPage");
            Integer count = (Integer) getTer(page, "count");
            if (pageCount <= curPage) {
                out.put("isLastPage", 1);
            } else {
                out.put("isLastPage", 0);
            }
            out.put("count", count);
        } catch (Exception e) {
            outList = new ArrayList<Map<String, Object>>();
            out.put("isLastPage", 0);
            out.put("count", 0);
        }
        out.put("list", outList);
        return out;
    }

    /**
     * 分页对象根据参数获取到map集合
     *
     * @param page
     * @param showPropertyArray
     * @param <P>
     * @param <E>
     * @return
     */
    public static <P, E> Map<String, Object> pageToMap(P page, String[] showPropertyArray) {
        return pageToMap(page, showPropertyArray, null, null);
    }

    //接口精简字段工具结束================================================

    //------测试静态方法----------
    public static void main(String[] argv) {

//        List sort = new ArrayList();
//        sort.add(null);
//        sort.add("0");
//        sort.add("2.0");
//        sort.add(1);
//        sort.add(1.5);
//        listSort(sort);
//        Object temp = sort;
//
//        LinkedHashSet<String> set = new LinkedHashSet<>();
//        set.add("1");
//        set.add("2");
//        set.add("1");
//        System.out.println("---");

        String ids = "1,";
        System.out.println(ids);
        Map<String, String> idsMap = strToMap(ids);
        System.out.println(listToStr(mapToList(idsMap)));
        String str = "[{\"content\":\"创意美术课上，孩子们用黑色水笔来勾画虾细小的身体部位  (眼睛、触角、虾足)当然也有对虾的想象进行的创作。北北: \\\"Helen， 你看我的胡子，嘿嘿嘿~”-边说着手里还拿着笔一-边画，真是让人哭笑不得，明明是纸上画虾，结果把自己变成了一只虾,还好有细心的蒋丹姐姐给清洗干净。\",\"id\":264883,\"name\":\"recordText\",\"pageId\":87679,\"paperId\":1637,\"type\":1}]";
        //DJson dJson = new DJson(str);
        System.out.println(listToStr(mapToList(idsMap)));
    }

    public static <E> boolean isBaseClass(E entity) {
        if (null != entity && (entity instanceof String
                || entity instanceof Double
                || entity instanceof Float
                || entity instanceof Long
                || entity instanceof Integer
                || entity instanceof Date
                || entity instanceof StringBuffer
                || entity instanceof String)
        ) {
            return true;
        } else {
            return false;
        }
    }

    public static String baseClassToStr(Object entity) {
        if (isBaseClass(entity)) {
            if (entity instanceof Date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return format.format(entity);
            } else {
                return String.valueOf(entity);
            }
        } else {
            return String.valueOf(entity);
        }
    }

    /**
     * 恢复旧vo对象删除状态并且转成DO
     *
     * @param oldVMap
     * @param updateDList
     * @param key
     * @param <V>
     * @param <D>
     * @return
     */
    public static <V, D> boolean oldToUpdate(Map<String, V> oldVMap, List<D> updateDList, String key) {
        if (null != oldVMap.get(key)) {
            V oneOldV = oldVMap.get(key);
            try {
                if (equal(getTer(oneOldV, "deleteStatus"), -1)) {
                    setTer(oneOldV, "deleteStatus", 1);
                    updateDList.add((D) invokeReturn(oneOldV, "toDO"));
                }
                oldVMap.remove(key);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除旧DO对象
     *
     * @param oldVMap
     * @param <V>
     * @param <D>
     * @return
     */
    public static <V, D> List<D> oldToDel(Map<String, V> oldVMap) {
        List<D> delDList = new ArrayList<>();
        if (null != oldVMap && oldVMap.size() > 0) {
            for (V oneDelV : oldVMap.values()) {
                try {
                    if (null != oneDelV && equal(getTer(oneDelV, "deleteStatus"), 1)) {
                        setTer(oneDelV, "deleteStatus", -1);
                        delDList.add((D) invokeReturn(oneDelV, "toDO"));
                    }
                } catch (Exception e) {
                }
            }
        }
        return delDList;
    }


    //------------------------------------以下为私有静态方法，仅仅提供给自身使用------------------------------------------------


    /**
     * @param sortObj1
     * @param sortObj2
     * @param type     ASC DESC
     * @return
     */
    private static int comparison(Object sortObj1, Object sortObj2, Direction type) {
        if (null == type || (null == sortObj1 && null == sortObj2)) {
            return 0;
        }
        if (null == sortObj2 && null != sortObj1) {
            return type == Direction.ASC ? -1 : 1;
        } else if (null == sortObj1 && null != sortObj2) {
            return type == Direction.DESC ? 1 : -1;
        }
        String sortVal1 = String.valueOf(sortObj1);
        String sortVal2 = String.valueOf(sortObj2);
        if (isNumber(sortObj1) && isNumber(sortObj2)) {
            Double sortDouble1 = Double.valueOf(sortVal1);
            Double sortDouble2 = Double.valueOf(sortVal2);
            return type == Direction.DESC ? sortDouble2.compareTo(sortDouble1) : sortDouble1.compareTo(sortDouble2);
        } else {
            //这里写比较方法
            return type == Direction.DESC ? sortVal2.compareTo(sortVal1) : sortVal1.compareTo(sortVal2);
        }
    }

    private static <E> boolean isMap(E entity) {
        if (null != entity && entity instanceof Map) {
            return true;
        } else {
            return false;
        }
    }

    private static <E> boolean isList(E entity) {
        if (null != entity && entity instanceof List) {
            return true;
        } else {
            return false;
        }
    }

    private static <E> boolean isSet(E entity) {
        if (null != entity && entity instanceof Set) {
            return true;
        } else {
            return false;
        }
    }

    private static String numberFormat(double num, int degree) {
        NumberFormat ddf1 = NumberFormat.getNumberInstance();
        ddf1.setMaximumFractionDigits(degree);
        ddf1.setMinimumFractionDigits(degree);
        ddf1.setGroupingUsed(false);
        return ddf1.format(num);
    }

    // 获取所有属性，包括父级的静态属性 [Created by 2018-08-03 15:15 fxLDJ]
    private static <E> List<Field> allField(E entity) {
        List<Field> list = new ArrayList<Field>();
        Class cls = entity.getClass();
        while (!cls.toString().equals(Object.class.toString())) {
            list.addAll(Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
        }
        return list;
    }

    /**
     * 反射调用对象实体的方法
     *
     * @param <E>
     * @param entity     对象实体
     * @param methodName 调用的方法名称
     * @param valueTypes 调用的方法传入值类型数值 （可以为空）
     * @param values     调用方法传入值数值（和valueTypes一一对应）（可以为空）
     * @return 返回方法调用结果
     */
    @SuppressWarnings("unchecked")
    private static <E> Object invoke(E entity, String methodName, Class[] valueTypes, Object[] values) throws Exception {
        return entity.getClass().getMethod(methodName, null == valueTypes ? new Class[]{} : valueTypes).invoke(entity, null == values ? new Object[]{} : values);
    }

    /**
     * 传入值调用对象方法，不返回结果
     *
     * @param <E>
     * @param entity
     * @param methodName
     * @param value
     */
    private static <E, V> void invokeNoReturn(E entity, String methodName, V value) throws Exception {
        try {
            invoke(entity, methodName, null == value ? null : new Class[]{value.getClass()}, null == value ? null : new Object[]{value});
        } catch (Exception e) {
            Class clazz = checkMethodNameClass(entity, methodName);
            if (null != clazz) {
                invoke(entity, methodName, null == value ? null : new Class[]{clazz}, null == value ? null : new Object[]{value});
            }
        }
    }

    /**
     * 调用对象方法，返回结果
     *
     * @param <E>
     * @param entity
     * @param methodName
     * @return
     */
    private static <E> Object invokeReturn(E entity, String methodName) throws Exception {
        return invoke(entity, methodName, null, null);
    }

    /**
     * 手写字母大写
     *
     * @param str
     * @return
     */
    private static String toUpperFirst(String str) {
        return isEmpty(str) ? str : str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
    }

    private static String toLowerFirst(String str) {
        return isEmpty(str) ? str : str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toLowerCase());
    }

    /**
     * 检查字符串是否为空
     *
     * @param str
     * @return
     */
    private static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }

    /**
     * new一个对象你指定属性的对象
     *
     * @param <<E>>
     * @param entity
     * @param property
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <E, O> O newEntity(E entity, String property) {
        property = property.trim();
        try {
            String className = entity.getClass().getMethod("get" + toUpperFirst(property)).getGenericReturnType().toString().replaceAll("^class +", "").trim();
            return (O) Class.forName(className).newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断是否string 或者基本数据类型
     *
     * @param obj
     * @return
     */
    private static boolean isWrapClass(Object obj) {
        return obj instanceof String || obj instanceof Number || obj instanceof Date;
    }

    /**
     * 列表转基础列表或者map列表
     *
     * @param list
     * @param decimalNum
     * @param datePattern
     * @param <E>
     * @return
     */
    private static <E> List listToBOMList(List<E> list, Integer decimalNum, String datePattern) {
        List outList = new ArrayList<>();
        if (null == list || list.size() <= 0) {
            return outList;
        }
        for (E e : list) {
            if (isBaseClass(e)) {
                outList.add(formatValue(e, decimalNum, datePattern));
            } else if (isList(e)) {
                outList.add(listToBOMList((List) e, decimalNum, datePattern));
            } else if (isSet(e)) {
                List listValue = new ArrayList();
                listValue.addAll((Set) e);
                outList.add(listToBOMList(listValue, decimalNum, datePattern));
            } else {
                outList.add(entityToMap(e, null, decimalNum, datePattern));
            }
        }
        return outList;
    }

    /**
     * 格式化数据
     *
     * @param value
     * @param decimalNum
     * @param datePattern
     * @return
     */
    private static Object formatValue(Object value, Integer decimalNum, String datePattern) {
        if (isBaseClass(value)) {
            if (null != decimalNum && (value instanceof Double || value instanceof Float)) {
                value = numberFormat(Double.valueOf(value.toString()), decimalNum);
            } else if (value instanceof Date) {
                SimpleDateFormat format = null == datePattern ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") : new SimpleDateFormat(datePattern);
                value = format.format(value);
            }
        }
        return value;
    }
}
