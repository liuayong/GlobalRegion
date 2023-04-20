package com.hspedu.pojo;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

/**
 * https://blog.csdn.net/weixin_49114503/article/details/125455179
 * https://blog.csdn.net/qq_34706514/article/details/124427524
 */
public class ReturnMore {

    @Test
    public void returnTwo() {
        Pair<Boolean, String> pair = new ImmutablePair<>(true, "张三");
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight());

        pair = Pair.of(true, "张三");
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight());
    }

    @Test
    public void returnThree() {
        Triple<String, Integer, String> triple = new ImmutableTriple<>("张三", 11, "男");
        System.out.println(triple.getLeft());
        System.out.println(triple.getMiddle());
        System.out.println(triple.getRight());

        triple = Triple.of("张三", 11, "男");
        System.out.println(triple.getLeft());
        System.out.println(triple.getMiddle());
        System.out.println(triple.getRight());
    }

}
