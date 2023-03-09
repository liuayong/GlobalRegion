package com.hspedu.java8.stream;

import com.byd.tool.PrintUtil;
import com.hspedu.pojo.User;
import com.hspedu.util.MapUtil;
import com.hspedu.util.ReflectionUtils;
import com.mexue.middle.school.util.BeanUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeTest {

    /**
     * 两个list《map》中的map合并为一个list《map》,新的list中的每个map包含了之前的两个listmap的key
     */
    public static void mergeTwoListmapToOneListmap(final String mergeKey) {

        List<Map<String, Object>> lists = new ArrayList<>();

        List<Map<String, Object>> lists1 = new ArrayList<>();
        List<Map<String, Object>> lists2 = new ArrayList<>();


        //--------------lists1--------------------
        Map<String, Object> h1 = new HashMap<>();
        h1.put("name", "fdsa0");
        h1.put("2", "fdsa0");
        h1.put("3", "fdsa0");
        h1.put("4", "fdsa0");

        Map<String, Object> h2 = new HashMap<>();
        h2.put("name", "fdsa00");
        h2.put("2", "fdsa00");
        h2.put("3", "fdsa00");
        h2.put("4", "fdsa00");

        lists1.add(h1);
        lists1.add(h2);

        //--------------lists2--------------------

        Map<String, Object> h3 = new HashMap<>();
        h3.put("name", "fdsa0");
        h3.put("21", "fdsa1");
        h3.put("31", "fdsa1");
        h3.put("41", "fdsa1");

        Map<String, Object> h4 = new HashMap<>();
        h4.put("name", "fdsa00");
        h4.put("21", "fdsa2");
        h4.put("31", "fdsa2");
        h4.put("41", "fdsa2");

        lists2.add(h3);
        lists2.add(h4);


        PrintUtil.println(lists1);
        PrintUtil.println(lists2);

        System.out.println("++++++++++++++++++++++++++++++++++++++++\n");
        //测试
        //mergeKey="name";

        lists1.parallelStream().forEach(x -> {

            Map<String, Object> y2 = lists2.parallelStream().filter(y -> y.get(mergeKey).toString().equals(x.get(mergeKey).toString()))
                    .findFirst().get();

            List<Map<String, Object>> sublist = Arrays.asList(x, y2);


            Map<String, Object> merged = sublist.stream()
                    .map(Map::entrySet)
                    .flatMap(Set::stream)
                    .distinct()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            lists.add(merged);

        });
        System.out.println("----------list--mergetMap---------");
        lists.forEach(x -> {
            System.out.println(x);
        });
        PrintUtil.println(lists);
    }


    @Test
    public void mergeTwoToOne() {
        // name相同的两个map 合并在一起 组成list的一个元素
        mergeTwoListmapToOneListmap("name");


    }

    @Test
    public void testMapMerge1() {
        String str = "[{active_user=2, company_id=13}, {active_user=1, company_id=126}, {company_id=13, material_num=13}, {company_id=126, material_num=2}, {company_id=13, learning_duration=4315.0}, {company_id=126, learning_duration=5.0}, {company_id=13, daily_answer_times=2}, {company_id=126, daily_answer_times=2}, {company_id=126, exam_times=1}]";
        // 转换为json, 在转换为maplist, 将map的toString字符串再还原为map结构


        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("one", "一");
        map1.put("two", "二");
        map1.put("three", "三");

        Map<String, String> map2 = new HashMap<String, String>();
        map1.put("ten", "十");
        map1.put("nine", "九");
        map1.put("eight", "八");
        Map<String, String> combineResultMap = MapUtil.mergeMap2(map1, map2);


        // 合并后打印出所有内容
        for (Map.Entry<String, String> entry : combineResultMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        PrintUtil.println(combineResultMap);
    }


    /**
     * https://www.cnblogs.com/sunliyuan/p/12420455.html
     */
    @Test
    public void testMapMerge2() {
        // todo liuayong
        String str = "[{active_user=2, company_id=13}, {active_user=1, company_id=126}, {company_id=13, material_num=13}, {company_id=126, material_num=2}, {company_id=13, learning_duration=4315.0}, {company_id=126, learning_duration=5.0}, {company_id=13, daily_answer_times=2}, {company_id=126, daily_answer_times=2}, {company_id=126, exam_times=1}]";
        // 转换为json, 在转换为maplist, 将map的toString字符串再还原为map结构

    }

    private static Map<String, User> map1 = null;
    private static Map<String, User> map2 = null;

    static {
        map1 = new HashMap<>();
        User employee1 = new User(1L, "Henry");
        map1.put(employee1.getName(), employee1);
        User employee2 = new User(22L, "Annie");
        map1.put(employee2.getName(), employee2);
        User employee3 = new User(8L, "John");
        map1.put(employee3.getName(), employee3);

        map2 = new HashMap<>();
        User employee4 = new User(2L, "George");
        map2.put(employee4.getName(), employee4);
        User employee5 = new User(3L, "Henry");
        map2.put(employee5.getName(), employee5);
    }

    /**
     *
     */
    @Test
    public void mapMerge1() {

/*

        Map<String, User> map3 = new HashMap<>(map1);

        map3.merge(key, value, (v1, v2) -> new User(v1.getUid(),v2.getName())

                map2.forEach(
                        (key, value) -> map3.merge(key, value, (v1, v2) -> new User(v1.getId(),v2.getName())));
*/

    }

    /**
     * https://blog.csdn.net/w605283073/article/details/82987157
     */
    @Test
    public void mapMerge2() {
        Stream combined = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream());
        PrintUtil.println(combined.collect(Collectors.toList()));

        // Map<String, User> result = combined.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    @Test
    public void mapMerge3() {
        Map<String, User> map3 = map2.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> new User(v1.getUid(), v2.getName()),
                        () -> new HashMap<>(map1)));

        PrintUtil.println(map3);
    }

    @Test
    public void mapMerge2_1() {
        Map<String, User> collect = map1.entrySet().stream()
                .filter(entry -> !map2.containsKey(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        PrintUtil.println(collect);
    }

    @Test
    public void reflect() throws IllegalAccessException {

        User user = new User(1L, "Henry");
        Field[] declaredFields = user.getClass().getDeclaredFields();

        for (Field f : declaredFields) {
            System.out.print(f.getName() + " ");//名称
            f.setAccessible(true);
            System.out.print(f.get(user) + " ");    // cannot access a member of class com.hspedu.pojo.User with modifiers "private"
            System.out.println(ReflectionUtils.getFieldValue(user, f.getName()));
        }

        Set<String> fileds = BeanUtil.getFileds(user);
        PrintUtil.println(fileds);
    }

}