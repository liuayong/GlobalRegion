package com.hspedu.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    public int compareTo(@NotNull User o) {
        return 0;
    }

    public int compareTo(@NotNull Student o) {
        return 0;
    }

    @Override
    /**
     * 以Student的姓名升序排列
     */
    public int compare(Student o1, Student o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
        // return String::compareTo;

    }


}
