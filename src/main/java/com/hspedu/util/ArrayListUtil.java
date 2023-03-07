package com.hspedu.util;

import com.hspedu.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: GlobalRegion
 * @Description 在Java中，经常遇到需要List与数组互相转换的场景。
 * List转换成数组，可以使用List的toArray()或者toArray(T[] a)方法。
 * 数组转换成List，可以使用Arrays.asList()或者Collections.addAll()方法。
 * 如果仅仅为了打印数组，不需要把数组转换成List，
 * 可以使用Arrays.toString()方法。
 * <p>
 * 作者：木木与呆呆
 * 链接：https://www.jianshu.com/p/7eee157f74fc
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @Author: Administrator
 * @Create: 2023/3/5
 **/
@Slf4j
public class ArrayListUtil {
    
    
    public static List<String> getUserNames() {
        return User.getUserList().stream().map(User::getName).collect(Collectors.toList());
    }
    
    @NotNull
    private String[] getArrayData() {
        List<String> userNames = getUserNames();
        String[] toArray = ArrayListUtil.toArray(userNames);
        return toArray;
    }
    
    public static String[] toArray(List<String> list) {
        
        String[] strings = new String[list.size()];
        String[] strArray = list.toArray(strings);
        
        //log.debug("array1={}, size1={}, array2={}, size2={}", strings, strings.length, strArray, strArray.length);
        //log.debug("size相同 是否为同一个对象={}, equals={}", strings == strArray, strArray.equals(strings));
        return strArray;
    }
    
    /**
     * 一.最常见方式（未必最佳）
     * 通过 Arrays.asList(strArray) 方式,将数组转换List后，不能对List增删，只能查改，否则抛异常 UnsupportedOperationException
     *
     * @param array
     * @return
     */
    public static List<String> toList(String[] array) {
        return Arrays.asList(array);
    }
    
    /**
     * 二.数组转为List后，支持增删改查的方式
     * 二.数组转为List后，支持增删改查的方式
     *
     * @param array
     * @return
     */
    public static List<String> toList2(String[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
    
    /**
     * 三.通过集合工具类Collections.addAll()方法(最高效)
     *
     * @param array
     * @return
     */
    public static List<String> toList3(String[] array) {
        List<String> strList = new ArrayList<>(array.length);
        Collections.addAll(strList, array);
        return strList;
    }
    
    /**
     * 如果JDK版本在1.8以上，可以使用流stream来将下列3种数组快速转为List，分别是int[]、long[]、double[]，其他数据类型比如short[]、byte[]、char[]，在JDK1.8中暂不支持。由于这只是一种常用方法的封装，不再纳入一种崭新的数组转List方式，暂时算是java流送给我们的常用工具方法吧。
     * ————————————————
     * 版权声明：本文为CSDN博主「大脑补丁」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/x541211190/article/details/79597236
     * List<Integer> intList= Arrays.stream(new int[] { 1, 2, 3, }).boxed().collect(Collectors.toList());
     * List<Long> longList= Arrays.stream(new long[] { 1, 2, 3 }).boxed().collect(Collectors.toList());
     * List<Double> doubleList= Arrays.stream(new double[] { 1, 2, 3 }).boxed().collect(Collectors.toList());
     *
     * @param array
     * @return
     */
    public static List<String> toList4(String[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }
    
    
    @Test
    public void testListToArray() {
        List<String> userNames = getUserNames();
        String[] toArray = ArrayListUtil.toArray(userNames);
        log.info("LIST转数组  toArray={}, length={}", ArrayListUtil.toArray(userNames), toArray.length);
    }
    
    @Test
    public void testArrayToList() {
        String[] toArray = getArrayData();
        List<String> list = ArrayListUtil.toList(toArray);
        log.info("数组转List  toList ={}, size={}", list, list.size());
        log.info("数组转List  toList2={} ", ArrayListUtil.toList2(toArray));
        log.info("数组转List  toList3={} ", ArrayListUtil.toList3(toArray));
        log.info("数组转List  toList4={} ", ArrayListUtil.toList4(toArray));
    }
    
    public static void main(String[] args) {
        
        String[] strArray = new String[2];
        List list = Arrays.asList(strArray);
        //对转换后的list插入一条数据
        list.add("1");
        System.out.println(list);
    }
    
}
