package com.hspedu.java8;

import com.byd.tool.PrintUtil;
import com.google.common.base.Supplier;
import com.hspedu.pojo.Student;
import com.littlefox.area.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Project: GlobalRegion
 * @Description 在这篇博文中，如何用逗号分隔的字符串转换列表。这包括以下例子
 * <p>
 * 将自定义对象的列表转换为逗号分隔的字符串
 * 将字符串/长条/整数的列表转换为逗号分隔的字符串。
 * @Author: Administrator
 * @Create: 2023/3/5
 **/
@Slf4j
public class ListTest {


    /**
     * List< String>转Map<String, List< Integer>>
     * https://blog.csdn.net/BigBigHang/article/details/122056100
     */
    @Test
    public void test1() {
        List<String> names = Arrays.asList("小明", "小红", "小刚", "小丽");
        Map<String, List<Object>> listMap = names.stream()
                .collect(Collectors.toMap(Function.identity(), e -> new ArrayList<>()));
        PrintUtil.println(listMap);
    }

    /**
     * // 将流的元素添加到现有列表的通用方法
     * https://www.techiedelight.com/zh/add-elements-stream-existing-list-java/
     *
     * @param target
     * @param source
     * @param <T>
     */
    public static <T> void addToList(List<T> target, Stream<T> source) {
        source.collect(Collectors.toCollection(() -> target));
    }

    @Test
    public void test2_1() {
        String[] array = {"a", "b", "c", "d", "e"};
        Stream<String> stream = Arrays.stream(array);
        Supplier<Stream<String>> streamSupplier = () -> Stream.of(array);
        //重新获取一个新的stream
        streamSupplier.get().forEach(System.out::println);
        //获取一个别的新的stream
        long count = streamSupplier.get().filter("b"::equals).count();
        System.out.println(count);
    }

    @Test
    public void test2() {
        Stream<Integer> stream = Stream.of(4, 5);
        Supplier<Stream<Integer>> streamCopier = () -> Stream.of(4, 5);
        System.out.println(streamCopier.get() == streamCopier.get());

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        addToList(list, stream);

        PrintUtil.println(list);

        // stream has already been operated upon or closed
        //List<Integer> collect1 = stream.sequential().collect(Collectors.toCollection(() -> list));

        List<Integer> collect1 = streamCopier.get().sequential().collect(Collectors.toCollection(() -> list));
        PrintUtil.println(collect1);

    }

    /**
     * https://www.logicbig.com/tutorials/core-java-tutorial/java-util-stream/sequential-vs-parallel.html
     */
    @Test
    public void test2_2() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        Stream<Integer> stream = Stream.of(4, 5);
        // stream.parallel
        List<Integer> collect1 = stream.sequential().collect(Collectors.toCollection(() -> list));
        PrintUtil.println(collect1);
    }

    /**
     * https://blog.51cto.com/u_15522232/5094864
     */
    // java lambda表达式将list转 map<String,List<String>>
    @Test
    public void test_groupingBy() {
        List<String> names = Arrays.asList("小明", "小红", "小刚", "小丽", "小红", "小刚");
        Map<String, List<String>> group1 = names.stream().collect(Collectors.groupingBy(e -> e));
        PrintUtil.println(group1);
        List<Student> studentList = Student.getList();
        //PrintUtil.println(studentList);
        Map<Integer, List<Student>> group2 = studentList.stream().collect(Collectors.groupingBy(Student::getAge));
        PrintUtil.println(group2);

        // 转换为map，然后值根据排序获取最大的一个
        Map<Integer, Object> map = studentList.stream().collect(Collectors.groupingBy(Student::getAge,
                // e -> e.get().getGrade()
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Student::getGrade)), e -> e.get())
        ));
        PrintUtil.println(map);

        //materielList.stream().collect(Collectors.groupingBy(BomDto::getRootId,
        //        Collectors.collectingAndThen(Collectors.toCollection( () -> new TreeSet<>(Comparator.comparing(BomDto::getMeasureParentCode))), ArrayList::new) ))

        // groupby 之后再对集合进行去重操作
        Map<Integer, ArrayList<Student>> map2 = studentList.stream().collect(Collectors.groupingBy(Student::getAge,
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getGrade))),
                        ArrayList::new)));
        PrintUtil.println(map2);

    }

    /**
     * https://blog.csdn.net/qq_41107231/article/details/117426667
     */
    @Test
    public void test3() {
        List<Student> studentList = Student.getList();
        // studentList.stream().forEach
        studentList.forEach(PrintUtil::printWait);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        studentList.parallelStream().forEach(PrintUtil::printWait);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");


    }

    /**
     * https://blog.csdn.net/qq_41107231/article/details/117426667
     */
    @Test
    public void findOne() {
        List<Student> studentList = Student.getList();

        Optional<Student> first1 = studentList.parallelStream().findFirst();
        Optional<Student> first2 = studentList.stream().findFirst();

        PrintUtil.println(first1);
        PrintUtil.println(first2.get());
        Optional<Student> first = studentList.parallelStream().findAny();
        System.out.println(first);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
    }

    @Test
    public void sum() {
        List<Student> studentList = Student.getList();

        Stream<Float> stream = studentList.stream().map(Student::getGrade);
        Optional<Float> reduce = stream.reduce(Float::sum);
        PrintUtil.println(reduce);
        System.out.println(reduce.get());

        double sum = studentList.stream().mapToDouble(Student::getGrade).sum();
        PrintUtil.println(sum);
        double sum1 = studentList.stream().map(Student::getGrade).mapToDouble(e -> e).sum();
        System.out.println("sum1 = " + sum1);
        Optional<Float> reduce1 = studentList.stream().map(Student::getGrade).reduce(Float::sum);
        PrintUtil.println(reduce1);
        Float reduce1_2 = studentList.stream().map(Student::getGrade).reduce(0F, Float::sum);
        PrintUtil.println(reduce1_2);

        BigDecimal reduce2 = studentList.stream().map(e -> BigDecimal.valueOf(e.getGrade())).reduce(BigDecimal.ZERO, BigDecimal::add);
        PrintUtil.println(reduce2);
        Double reduce3 = studentList.stream().map(e -> (double) e.getGrade()).reduce(0.0, Double::sum);
        PrintUtil.println(reduce3);


    }


    /**
     * https://blog.csdn.net/BASK2312/article/details/128426538
     * https://www.moonapi.com/news/20685.html
     */
    @Test
    public void testList_1() {
        List<Integer> collect = IntStream.rangeClosed(65, 90).mapToObj(e -> e).collect(Collectors.toList());
        PrintUtil.println(collect);

        List<Character> chars = IntStream.rangeClosed(65, 90)
                .mapToObj(x -> (char) x)
                .collect(Collectors.toList());

        PrintUtil.println(chars);
        Character lastItem = chars.get(chars.size() - 1);
        System.out.println(lastItem);        // 'Z'
    }


    @Test
    public void test4() {
        List<Integer> collect = Stream
                .iterate(0, n -> n + 1).limit(10).collect(Collectors.toList());

        PrintUtil.println(collect);
    }

}
