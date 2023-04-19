package com.hspedu.pojo;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

public class TripleDemo {


    @Test
    public void test1() {
        Pair<Integer, Integer> pair = new ImmutablePair<>(1, 2);
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight());

        Triple<String, String, String> triple = new ImmutableTriple<>("1", "2", "3");
        System.out.println(triple.getLeft());
        System.out.println(triple.getMiddle());
        System.out.println(triple.getRight());


        Triple<Integer, Boolean, String> triple2 = Triple.of(1, false, "hello");
        System.out.println(triple2);
        System.out.println(triple2.getMiddle());


        Pair<String, Integer> pair2 = Pair.of("hello", 111);
        System.out.println(pair2);
        System.out.println(pair2.getRight());

    }

}
