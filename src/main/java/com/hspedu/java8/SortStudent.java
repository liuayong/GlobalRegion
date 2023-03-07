package com.hspedu.java8;

import com.byd.tool.PrintUtil;
import com.hspedu.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/8
 **/
@Slf4j
public class SortStudent {
    //年龄比较器
    Comparator<Student> comparatorAge = new Comparator<Student>() {
        public int compare(Student p1, Student p2) {
            if (p1.getAge() > p2.getAge())
                return 1;
            else if (p1.getAge() < p2.getAge())
                return -1;
            else
                return 0;
        }
    };
    
    //成绩比较器
    Comparator<Student> comparatorGrade = new Comparator<Student>() {
        public int compare(Student p1, Student p2) {
            if (p1.getGrade() > p2.getGrade())
                return 1;
            else if (p1.getGrade() < p2.getGrade())
                return -1;
            else
                return 0;
        }
    };
    
    public Student[] ageSort(Student[] s) {
        Arrays.sort(s, comparatorAge);
        return s;
    }
    
    public Student[] gradeSort(Student[] s) {
        Arrays.sort(s, comparatorGrade);
        return s;
    }
    
    @Test
    public void test1() {
        List<Student> stuList = Student.getList();
        Student[] tArr = new Student[stuList.size()];
        Student[] list = stuList.toArray(tArr);
        System.out.println(tArr.equals(list));
        System.out.println(tArr == list);
        System.out.println(Arrays.toString(tArr));
        System.out.println(Arrays.toString(list));
        
        
        Student[] agePrint = this.ageSort(list);
        PrintUtil.println(agePrint);
        
        Student[] gradePrint = this.gradeSort(list);
        PrintUtil.println(gradePrint);
        
    }
    
    /*
     * 题目：
     * 输入一个正整数数组,把数组里所有数字拼接起来排成一个数,打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3,32,321},则打印出这三个数字能排成的最小数字为321323。
     */
    @Test
    public void test2() {
        Integer[] integers = {3, 32, 321, 2};
        log.info("排列成的最小数字: " + PrintMinNumber(integers));
    }
    
    /**
     * employees.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
     * Collections.sort(employees, (o1, o2) -> o1.getName().compareTo(o2.getName()));
     */
    @Test
    public void test3() {
        
        List<Student> stuList = Student.getList();
        PrintUtil.println(stuList);
        //stuList.sort(Comparator.comparing(Student::getAge));
        //List<Student> sortList =
        //        stuList.stream().sorted(Comparator.comparing(Student::getGrade)).collect(Collectors.toList());
        
        List<Student> sortList =
                stuList.stream().sorted(Comparator.comparing(Student::getGrade, Comparator.reverseOrder())).collect(Collectors.toList());
        
        //List<Student> sortList = stuList.stream().sorted((o1, o2) -> (int) (o1.getGrade() - o2.getGrade())).collect(Collectors.toList());
        PrintUtil.println(stuList);
        PrintUtil.println(sortList);
    }
    
    @Test
    public void test4() {
        
        List<Student> stuList = Student.getList();
        PrintUtil.println(stuList);
        Comparator<Float> floatComparatorReversed = (s1, s2) -> {
            return s2.compareTo(s1);
        };
        
        // Comparator.naturalOrder
        Comparator<Float> floatComparatorNaturalOrder = (s1, s2) -> {
            return s1.compareTo(s2);
        };
        List<Student> sortList =
                stuList.stream().sorted(Comparator.comparing(Student::getGrade, (s1, s2) -> {
                    log.info("s1 = {} s1Type={}, s2={}, s2Type={}", s1, s1.getClass().getName(), s2, s2.getClass().getName());
                    return s1.compareTo(s2);
                })).collect(Collectors.toList());
        PrintUtil.println(stuList);
        PrintUtil.println(sortList);
    }
    
    
    @Test
    public void testReverse() {
        
        List<Student> stuList = Student.getList();
        
        List<Float> gradeFlosts1 = stuList.stream().map(Student::getGrade).sorted(Comparator.comparing(Float::intValue)).collect(Collectors.toList());
        System.out.println("gradeFlosts1:" + Arrays.toString(gradeFlosts1.toArray()));
        List<Float> gradeFlosts2 =
                stuList.stream().map(Student::getGrade).sorted(Float::compareTo).collect(Collectors.toList());
        System.out.println("gradeFlosts2:" + Arrays.toString(gradeFlosts2.toArray()));
        
        List<Float> gradeFlosts3 = stuList.stream().map(Student::getGrade)
                .sorted((o1, o2) -> o1 < o2 ? 1 : o1 > o2 ? -1 : 0).collect(Collectors.toList()); // 倒序
        System.out.println("gradeFlosts3:" + Arrays.toString(gradeFlosts3.toArray()));
        
        List<Float> gradeFlosts6 = stuList.stream().map(Student::getGrade)
                .sorted((o1, o2) -> Float.compare(o1, o2)).collect(Collectors.toList()); // 升序
        System.out.println("gradeFlosts6:" + Arrays.toString(gradeFlosts6.toArray()));
        
        List<Float> gradeFlosts4 = stuList.stream().map(Student::getGrade)
                .sorted((o1, o2) -> o2.compareTo(o1)).collect(Collectors.toList()); // 倒序
        System.out.println("gradeFlosts4:" + Arrays.toString(gradeFlosts4.toArray()));
        
        List<Float> gradeFlosts5 = stuList.stream().map(Student::getGrade)
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList()); // 倒序
        System.out.println("gradeFlosts5:" + Arrays.toString(gradeFlosts5.toArray()));
        
        
        //stuList.stream().map(Student::getGrade).sorted(Comparator.comparing(Float::intValue)).collect(Collectors.toList());
        List<String> strings = stuList.stream().map(Student::getName).sorted()  // .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(Arrays.toString(strings.toArray()));
        PrintUtil.println(strings);
        PrintUtil.println(stuList);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++\n");
        
        // 降序排列1
        List<Student> sortList1 = stuList.stream().sorted(Comparator.comparing(Student::getGrade,
                Comparator.reverseOrder())).collect(Collectors.toList());
        PrintUtil.println(sortList1);
        Assert.assertEquals(Arrays.toString(gradeFlosts5.toArray()), Arrays.toString(sortList1.stream().map(Student::getGrade).toArray()));
        
        // 降序排列2
        List<Student> sortList2 =
                stuList.stream().sorted(Comparator.comparing(Student::getGrade).reversed()).collect(Collectors.toList());
        PrintUtil.println(sortList2);
        
        // 降序排列3
        //stuList.sort((o1, o2) -> o1.getGrade() < o2.getGrade() ? -1 : o1.getGrade() > o2.getGrade() ? 1 : 0);
        //stuList.sort((o1, o2) -> o1.getGrade() < o2.getGrade() ? 1 : (o1.getGrade() > o2.getGrade() ? -1 : 0));
        stuList.sort((o1, o2) -> Float.compare(o2.getGrade(), o1.getGrade()));
        PrintUtil.println(stuList);
        
        
    }
    
    
    public static String PrintMinNumber(Integer[] s) {
        Arrays.sort(s, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String str1 = o1 + "" + o2;
                String str2 = o2 + "" + o1;
                
                //return str1.compareTo(str2);
                return -str2.compareTo(str1);
            }
        });
        
        PrintUtil.println(s);
        return Arrays.stream(s).map(e -> e + "").collect(Collectors.joining(""));
    }
    
    @Test
    public void testReversedSort() {
        int[] ns = {28, 12, 89, 73, 65, 18, 96, 50, 8, 36};
        // 排序前:
        System.out.println(Arrays.toString(ns));
        
        for (int i = 0; i < ns.length - 1; i++) {
            for (int j = 0; j < ns.length - i - 1; j++) {
                if (ns[j + 1] > ns[j]) {
                    int tmp = ns[j];
                    ns[j] = ns[j + 1];
                    ns[j + 1] = tmp;
                }
            }
        }
        // 排序后:
        System.out.println(Arrays.toString(ns));
        Assert.assertEquals("[96, 89, 73, 65, 50, 36, 28, 18, 12, 8]", Arrays.toString(ns));
        if (Arrays.toString(ns).equals("[96, 89, 73, 65, 50, 36, 28, 18, 12, 8]")) {
            System.out.println("测试成功");
        } else {
            System.out.println("测试失败");
        }
    }
}
