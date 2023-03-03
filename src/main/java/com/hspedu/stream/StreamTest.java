package com.hspedu.stream;


import com.hspedu.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class StreamTest {
    /**
     * 构造一个集合
     */
    public static List<User> getUserList() {
        User user2 = new User(2, "李", "BB");
        User user4 = new User(4, "马", "CCC");
        User user1 = new User(1, "张", "A");
        User user5 = new User(5, "赵", "CCC");
        User user3 = new User(3, "王", "BB");
        User user6 = new User(6, "钱", "CCC");

        List<User> list = new ArrayList<User>();
        list.add(user2);
        list.add(user4);
        list.add(user3);
        list.add(user1);
        list.add(user5);
        list.add(user6);

        return list;
    }

    /**
     * 打印出所有的 key 及 对应list中的元素
     */
    private static void print(Map<String, List<User>> groupMap) {
        groupMap.forEach((key, list) -> {
            System.out.println(key);
            list.forEach(System.out::println);
        });
        System.out.println("+++++++++++++++++++++++++++\n");
    }

    private static void print(List<User> list) {
        list.forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void sort() {
        List<User> list = getUserList();
        print(list);

        //根据 id 升序
        List<User> sortList = list.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        print(sortList);

        list.sort(Comparator.comparing(User::getId).reversed());
        print(list);

    }

    @Test
    public void linkedHashMap() {
        List<User> list = getUserList();
        //根据 id 升序
        // list = list.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println();

        //（1）这样分组，key 是无序的
        Map<String, List<User>> groupMap1 = list.stream().collect(Collectors.groupingBy(User::getType));
        print(groupMap1);


        //（2）这样分组，key 还是原来的顺序
        Map<String, List<User>> groupMap2 = list.stream().collect(Collectors.groupingBy(User::getType, LinkedHashMap::new, Collectors.toList()));
        // LinkedHashMap<String, List<User>> groupMap2 = list.stream().collect(Collectors.groupingBy(User::getType, LinkedHashMap::new, Collectors.toList()));
        print(groupMap2);


    }

    /**
     * https://blog.csdn.net/qq_43517448/article/details/121197755
     */
    @Test
    public void toMap() {
        List<User> list = getUserList();
        print(list);

        // 这样分组，key 是无序的
        Map<String, List<User>> groupByMap = list.stream().collect(Collectors.groupingBy(User::getType));
        System.out.println(groupByMap);

        // groupby方法有几个重载方法，上面使用的这个方法有3个参数
        // 第一个参数：分组按照什么进行分类
        // 第二个参数：分组结果最后用什么容器保存并返回，这里指定为LinkedHashMap
        // 第三个参数：分类后，对应的分类的结果如何收集
        // 保留顺序  这样分组，key 还是原来的顺序
        Map<String, List<User>> groupMap2 = list.stream()
                .collect(Collectors.groupingBy(User::getType, LinkedHashMap::new, Collectors.toList()));
        System.out.println(groupMap2);

        // mergeFunction用于设置遇到重复的key保留哪个的问题。(k1, k2) -> k1后面的哪个遇到重复的就保留那个key的值， 重复时保留 第一次出现的key
        Map<String, User> map1 = list.stream()
                .collect(Collectors.toMap(User::getType, Function.identity(), (k1, k2) -> k1, LinkedHashMap::new));

        System.out.println(map1);

        // 保留后面出现的key
        Map<String, User> map2 = list.stream().collect(Collectors.toMap(User::getType, e -> e, (k1,k2)->k2));
        System.out.println(map2);

        // Map<String, User> map3 = list.stream().collect(Collectors.toMap(User::getType, Function.identity()));
        // System.out.println(map3);
    }

}
