package com.hspedu.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 三元组

/**
 * https://www.techiedelight.com/zh/implement-3-tuple-triplet-java/
 * https://gist.github.com/xizzhu/940728509b0d6282173c078a895a7333
 *
 * @param <U>
 * @param <V>
 * @param <T>
 */
public class Triplet<U, V, T> {
    public final U first;       // 三元组的第一个字段
    public final V second;      // 三元组的第二个字段
    public final T third;       // 三元组的第三个字段

    // 用给定的值构造一个新的三元组
    private Triplet(U first, V second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public boolean equals(Object o) {
        /* 检查指定对象是否“等于”当前对象 */

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triplet triplet = (Triplet) o;

        // 调用底层对象的 `equals()` 方法
        if (!first.equals(triplet.first) ||
                !second.equals(triplet.second) ||
                !third.equals(triplet.third)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        /* 使用散列码计算对象的散列码
        底层对象 */

        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        result = 31 * result + third.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }

    // 用于创建三元组的类型化不可变实例的工厂方法
    public static <U, V, T> Triplet<U, V, T> of(U a, V b, T c) {
        return new Triplet<>(a, b, c);
    }

    public static void main(String[] args) {
        Triplet<String, Integer, Character> p1 = Triplet.of("David", 26, 'M');
        Triplet<String, Integer, Character> p2 = Triplet.of("Lisa", 20, 'F');
        Triplet<String, Integer, Character> p3 = Triplet.of("David", 26, 'M');

        List<Triplet<String, Integer, Character>> pairs = new ArrayList<>();
        pairs.add(p1);
        pairs.add(p2);
        pairs.add(p3);

        System.out.println(pairs);

        Set<Triplet<String, Integer, Character>> distinctTriplets = new HashSet<>(pairs);
        System.out.println(distinctTriplets);
    }
}