package com.hspedu.java8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.byd.tool.PrintUtil;
import com.hspedu.pojo.Student;
import com.hspedu.pojo.StudentComparator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
1.对于String或Integer这些已经实现Comparable接口的类来说，可以直接使用Collections.sort方法传入list参数来实现默认方式（正序）排序；
2.如果不想使用默认方式（正序）排序，可以通过Collections.sort传入第二个参数类型为Comparator来自定义排序规则；
3.对于自定义类型(如本例子中的Emp)，如果想使用Collections.sort的方式一进行排序，可以通过实现Comparable接口的compareTo方法来进行，如果不实现，则参考第2点；
4.jdk1.8的Comparator接口有很多新增方法，其中有个reversed()方法比较实用，是用来切换正序和逆序的

**/
@Slf4j
public class CollectionsSort {
    static List<Integer> intList = Arrays.asList(2, 3, 1);

    @Test
    public void test1() {
        System.out.println("before sort:");
        PrintUtil.println(intList);
        System.out.println("=========================");
        Collections.sort(intList, Comparator.reverseOrder());
        System.out.println("after sort:");
        PrintUtil.println(intList);
    }

    @Test
    public void test2() {
        System.out.println("before sort:");
        PrintUtil.println(intList);
        System.out.println("=========================");
        Collections.sort(intList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 返回值为int类型，大于0表示正序，小于0表示逆序
                return o2 - o1;
            }
        });
        System.out.println("after sort:");
        PrintUtil.println(intList);
    }

    @Test
    public void test3() {
        List<Student> list = Student.getList();
        // 意思是参数类型为List<Student>时，sort方法无法执行，原因是泛型没有继承Comparable接口
        // Collections.sort(list);
        PrintUtil.println(list);

        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                // return -1; // 倒序 o1 永远小于 o2
                return o1.getAge() - o2.getAge(); // 按照年龄排序
            }
        });
        PrintUtil.println(list);

    }

    @Test
    public void test4() {
        List<Student> list = Student.getList();
        PrintUtil.println(list);
        // 意思是参数类型为List<Student>时，sort方法无法执行，原因是泛型没有继承Comparable接口
        Collections.sort(list, new StudentComparator());
        PrintUtil.println(list);
    }

    /*
    在JDK1.7中一般使用工具类Collections（注意区分集合几口Collection），来对集合进行排序，集合中的对象类必须实现Comparable接口的compare方法，用于比较两个对象大小，但是这种方法只能通过比较内部的属性的大小来进行逻辑上的判断，通常Integer等装箱类和String都实现了compare方法。

    还有一种方式可以将排序规则作为入参放在Collections.sort方法中，这样可以引入对象外的其他参数。

    但是在JDK1.8中List增加了sort方法，入参为排序规则，实现也很简单
    Collections.sort(medias, new Comparator<MediaPojo>() {
                public int compare(MediaPojo o1, MediaPojo o2){
                    Integer i1 = order.indexOf(o1.getMediaName()); //order表示文件名列表，在方法体外引入
                    Integer i2 = order.indexOf(o2.getMediaName());
                    return i2.compareTo(i2);
                }
            });


    原文链接：https://blog.csdn.net/VICHOU_FA/article/details/80431785
     */
    @Test
    public void test5() {
        List<Student> list = Student.getList();
        PrintUtil.println(list);

        // 根据指定的Order 顺序进行排序
        List<String> order = new ArrayList<>();
        order.addAll(Arrays.asList("Banana p2", "Apple p3", "HAI p1", "good p4", "hello", "lyy", "morning"));


        Comparator<Student> specialOrder = new Comparator<>() {

            public int compare(Student o1, Student o2) {
                Integer i1 = order.indexOf(o1.getName()); //order表示文件名列表，在方法体外引入
                Integer i2 = order.indexOf(o2.getName());

                return i1.compareTo(i2);
            }
        };
        Collections.sort(list, specialOrder);

        PrintUtil.println(list);    // 2,3,1,4

        order.add(1, order.remove(3));  // 改变order的顺序   // 2,4,3,1
        System.out.println(Arrays.toString(order.toArray()));
        // 在JDK1.8中List增加了sort方法，入参为排序规则，实现也很简单
        list = Student.getList();
        list.sort(specialOrder);

        PrintUtil.println(list);
    }

    @Test
    public void test5_sepcial() {
        List<Student> list = Student.getList();
        PrintUtil.println(list);

        // 根据指定的Order 顺序进行排序
        ArrayList<String> order = new ArrayList<>();
        order.addAll(Arrays.asList("Banana p2", "Apple p32", "HAI p12", "good p4", "hello", "lyy", "morning"));
        List<String> studentNames = list.stream().map(Student::getName).collect(Collectors.toList());
        // 集合交集
        order.retainAll(studentNames);
        // PrintUtil.println(order);   // 改变了原集合 order
        // 差集
        studentNames.removeAll(order);  // 改变了原集合

        order.addAll(studentNames);
        List<Student> sortList1 = orderByList1(list, order);
        Assert.assertEquals(Arrays.toString(order.toArray()), Arrays.toString(sortList1.stream().map(Student::getName).toArray()));
        PrintUtil.println(sortList1);

        List<Student> sortList2 = orderByList2(list, order);
        Assert.assertEquals(Arrays.toString(order.toArray()), Arrays.toString(sortList2.stream().map(Student::getName).toArray()));
        PrintUtil.println(sortList2);

        List<Student> sortList3 = orderByList3(list, order);
        Assert.assertEquals(Arrays.toString(order.toArray()), Arrays.toString(sortList3.stream().map(Student::getName).toArray()));

        PrintUtil.println(sortList3);
    }


    @Test
    public void test6() {
        // 根据指定的Order 顺序进行排序
        List<String> order = new ArrayList<>();
        order.addAll(Arrays.asList("Banana p2", "Apple p3", "HAI p1", "good p4", "hello", "lyy", "morning"));
        List<Student> list = Student.getList();
        PrintUtil.println(list);

        List<Student> sortList = orderByList2(list, order);
        PrintUtil.println(sortList);
    }


    /**
     * List 根据指定的Order顺序对某个字段进行排序
     *
     * @param list
     * @param order
     */
    public List<Student> orderByList1(List<Student> list, List<String> order) {

        // Map<String, Student> studentMap = list.stream().collect(Collectors.toMap(Student::getName, Function.identity()));

        // LinkedHashMap 保留List的顺序
        Map<String, Student> studentMap = list.stream().collect(Collectors.toMap(Student::getName, Function.identity(),
                (k1, k2) -> k1, LinkedHashMap::new));
        // PrintUtil.println(studentMap);

        List<Student> sortList = new ArrayList<>(list.size());

        for (String orderItem : order) {
            Student student = studentMap.get(orderItem);
            if (student != null) {
                sortList.add(student);
            }
        }
        return sortList;
    }

    public List<Student> orderByList2(List<Student> list, List<String> order) {
        List<Student> sortList = new ArrayList<>(list.size());
        Map<Integer, Student> existsMap = new TreeMap<>();
        List<Student> notExistsList = new ArrayList<>();
        for (Student student : list) {
            int pos = order.indexOf(student.getName());
            if (pos >= 0) {
                existsMap.put(pos, student);
            } else {
                notExistsList.add(student);
            }
        }
        sortList.addAll(existsMap.values());
        sortList.addAll(notExistsList);
        return sortList;
    }

    public List<Student> orderByList3(List<Student> list, List<String> order) {
        // 深度拷贝
        // list = JSON.parseArray(JSON.toJSONString(list), Student.class);
        list = JSON.parseObject(JSON.toJSONString(list), new TypeReference<List<Student>>() {
        });
        Comparator<Student> specialOrder = new Comparator<>() {
            public int compare(Student o1, Student o2) {
                Integer i1 = order.indexOf(o1.getName()); //order表示文件名列表，在方法体外引入
                Integer i2 = order.indexOf(o2.getName());
                // 1,3,2,4
                // if (i1 == -1) {     // 2,4,1,3
                //     i1 = order.size() + 1;
                // }
                // if (i2 == -1) {
                //     i2 = order.size() + 1;
                // }

                return i1.compareTo(i2);
            }
        };
        Collections.sort(list, specialOrder);
        // list.forEach(System.out::println);
        return list;
    }


}
