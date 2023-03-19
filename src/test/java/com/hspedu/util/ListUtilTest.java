package com.hspedu.util;

import com.byd.tool.PrintUtil;
import com.hspedu.pojo.User;
import com.mexue.middle.school.search.PageInputVo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.*;

public class ListUtilTest {

    @Test
    public void splitList() {
        List<Long> idList = LongStream.rangeClosed(101, 1100).mapToObj(e -> e).collect(Collectors.toList());
        PrintUtil.println(idList);

        List<List<Long>> lists = ListUtil.splitList(idList, 800);
        PrintUtil.println(lists);

        List<List<Long>> lists2 = ListUtil.splitList2(idList, 800);
        PrintUtil.println(lists2);
        Assert.assertEquals(lists2, lists);


        System.out.println();
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);

        int splitSize = 3;
        for (int i = 0; i < 5; i++) {
            List<Integer> collect = integers.stream().skip(i * splitSize).limit(splitSize).collect(Collectors.toList());
            System.out.println(collect);
        }

        PrintUtil.println(ListUtil.splitList(integers, 2));

    }

    @Test
    public void getOnePageData() {
        List<User> userList = User.getUserList();
        int pageNum = 0;
        int pageSize = 2;

        PageInputVo pageInputVo = new PageInputVo(pageNum, pageSize);
        List<User> onePageData1 = ListUtil.getOnePageData(userList, pageInputVo);
        PrintUtil.println(onePageData1);

        // List<User> onePageData2 = ListUtil.getOnePageData2(userList, pageInputVo);
        // PrintUtil.println(onePageData2);

        List<User> onePageData3 = ListUtil.getOnePageData(userList, pageNum, pageSize);
        System.out.println(onePageData3);
        PrintUtil.println(onePageData3);       // todo liuayong 空集合 不打印
    }

    @Test
    public void getOnePageData2() {
    }
}