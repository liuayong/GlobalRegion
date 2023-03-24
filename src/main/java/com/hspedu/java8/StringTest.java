package com.hspedu.java8;

import com.byd.tool.PrintUtil;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.StringTokenizer;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/18
 **/
public class StringTest {
    
    private static String s = "Hello World 你好 中国！！";
    
    
    @Test
    public void test1() {
        
        for (int i = 0, len = s.length(); i < len; i++) {
            char ch = s.charAt(i);
            System.out.print(ch + " ");
        }
        System.out.println();
    }
    
    @Test
    public void test1_2() {
        
        for (int i = 0; i < s.length(); i++) {
            System.out.print(" " + s.substring(i, i + 1));
        }
    }
    
    @Test
    public void test2() {
        // 将字符串转换为 `char[]` 数组
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            System.out.print(aChar + " ");
        }
        System.out.println();
        
        PrintUtil.println(chars);
    }
    
    @Test
    public void test3() {
        // 将字符串转换为 `char[]` 数组
        char[] chars = s.toCharArray();
        Character[] array = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            array[i] = chars[i];
        }
        PrintUtil.println(array);
    }
    
    @Test
    public void test4() {
        char[] charArray = s.toCharArray();
        Character[] charObjectArray = ArrayUtils.toObject(charArray);
        PrintUtil.println(charObjectArray);
    }
    
    
    /**
     * 我们也可以使用 StringCharacterIterator 为字符串实现双向迭代的类。
     * https://www.techiedelight.com/zh/iterate-over-characters-string-java/
     */
    @Test
    public void test5() {
        CharacterIterator it = new StringCharacterIterator(s);
        char[] chars = new char[s.length()];
        int idx = 0;
        while (it.current() != CharacterIterator.DONE) {
            chars[idx++] = it.current();
            it.next();
        }
        
        PrintUtil.println(chars);
    }
    
    @Test
    public void test5_2() {
        CharacterIterator iter = new StringCharacterIterator(s);
        char[] chars = new char[s.length()];
        for (char c = iter.last(), idx = 0; c != CharacterIterator.DONE; c = iter.previous(), idx++) {
            chars[idx] = c;
        }
        PrintUtil.println(chars);
    }
    
    /**
     * https://www.techiedelight.com/zh/iterate-over-characters-string-java/
     */
    @Test
    public void test6() {
        // 如果 returnDelims 为真，则使用字符串本身作为分隔符
        StringTokenizer st = new StringTokenizer(s, s, true);
        
        while (st.hasMoreTokens()) {
            System.out.print(st.nextToken() + " ");
        }
        
        // 如果 returnDelims 为 false，则使用空字符串作为分隔符
        st = new StringTokenizer(s, "", false);
        
        while (st.hasMoreTokens()) {
            PrintUtil.println(st.nextToken());
        }
    }
    
    @Test
    public void test7() {
        String[] arr = s.split("");
        
        for (String ch : arr) {
            System.out.print(ch + " ");
        }
        System.out.println();
    }
    
    /**
     * https://www.techiedelight.com/zh/iterate-over-characters-string-java/
     * Guava’s Lists.charactersOf() 将指定字符串的视图作为不可变的字符列表返回。我们可以使用 for-each 循环或迭代器来处理不可变列表。
     * 请注意，此方法返回一个视图；这里没有实际的复制。
     */
    @Test
    public void test8() {
        // 使用 for-each 循环
        for (Character ch : Lists.charactersOf(s)) {
            System.out.print(ch + " ");
        }
        System.out.println();
        // Java 8 – 列表迭代器
        Lists.charactersOf(s).forEach(System.out::print);
    }
    
    /**
     * Java 8 提供了一种新方法， String.chars()，它返回一个 IntStream (整数流)表示字符串中字符的整数表示。
     * 此方法不返回所需的 Stream<Character> (出于性能原因)，但我们可以映射 IntStream 以这样一种方式，它会自动装箱成一个对象 Stream<Character>.有多种方法可以实现，如下所示：
     */
    @Test
    public void test9() {
        // 1. 隐式装箱到 `Stream<Character>`
        
        // 1.1。使用方法参考
        s.chars()
                .mapToObj(Character::toChars)
                .forEach(System.out::print);
        
        // 1.2。通过将 `int` 转换为 `char` 来使用 lambda 表达式
        s.chars()
                .mapToObj(i -> Character.valueOf((char) i))
                .forEach(System.out::print);
        System.out.println();
        s.chars()
                .mapToObj(i -> {
                    System.out.print(" " + i);
                    return (char) i;
                })
                .forEach(System.out::print);
        System.out.println();
        s.chars()
                .mapToObj(i -> new StringBuilder().appendCodePoint(i))
                .forEach(System.out::print);
        
        // 2. 没有装箱到 `Stream<Character>`
        
        s.chars()
                .forEach(i -> System.out.print(Character.toChars(i)));
        
        s.chars()
                .forEach(i -> System.out.print((char) i));
        
        System.out.println();
        
        StringBuilder stringBuilder = new StringBuilder();
        s.chars()
                .forEach(stringBuilder::appendCodePoint);
        
        System.out.println(stringBuilder);
    }
    
    @Test
    public void test10() {
        /* 1. 隐式装箱到 `Stream<Character>` */
        
        // 1.1。使用方法参考
        s.codePoints()
                .mapToObj(Character::toChars)
                .forEach(System.out::print);
        
        s.codePoints()
                .mapToObj(i -> new StringBuilder().appendCodePoint(i))
                .forEach(System.out::print);
        
        // 1.2。通过将 `int` 转换为 `char` 来使用 lambda 表达式
        s.chars()
                .mapToObj(i -> Character.valueOf((char) i))
                .forEach(System.out::print);
        
        s.codePoints()
                .mapToObj(i -> (char) i)
                .forEach(System.out::print);
        
        /* 2. 没有装箱到 `Stream<Character>` */
        
        s.codePoints()
                .forEach(i -> System.out.print(Character.toChars(i)));
        
        s.codePoints()
                .forEach(i -> System.out.print((char) i));
        
        s.chars()
                .forEach(i -> System.out.print(new StringBuilder()
                        .appendCodePoint(i)));
    }
    
    
    @Test
    public void test11() {
        String s = "Techie Delight";
        
        Field field = null;
        try {
            field = String.class.getDeclaredField("value");
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        
        char[] chars = new char[0];
        try {
            chars = (char[]) field.get(s);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        for (char ch : chars) {
            System.out.print(ch);
        }
    }
    
    @Test
    public void tt() {
        Character c1 = 'A';
        char ch = c1.charValue();
        char ch2 = 'b';
        PrintUtil.println(c1);
        PrintUtil.println(ch);
        PrintUtil.println(ch2);
    }
    
    // 遍历字符串的字符
    public static void main(String[] args) throws IllegalAccessException {
        String s = "Techie Delight";
        
        Field field = null;
        try {
            field = String.class.getDeclaredField("value");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);
        Object o = field.get(s);
        System.out.println(o);
        System.out.println(o.getClass().getName());
        PrintUtil.println(o);
        
        
        char[] chars = new char[0];
        try {
            chars = (char[]) field.get(s);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        for (char ch : chars) {
            System.out.print(ch);
        }
    }
}
