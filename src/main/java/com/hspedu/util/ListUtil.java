package com.hspedu.util;

import com.mexue.middle.school.search.PageInputVo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/4
 **/

public class ListUtil {


    /**
     * 拆分List
     *
     * @param list
     * @param splitSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int splitSize) {
        if (CollectionUtils.isEmpty(list) || splitSize < 1) {
            return new ArrayList<>();
        }
        int listSize = list.size();
        int pageNum = (int) Math.ceil(listSize / (splitSize + 0.0));
        List<List<T>> result = new ArrayList<>(pageNum);
        for (int i = 0; i < pageNum; i++) {
            int fromIndex = i * splitSize;
            // int toIndex = i == listSize - 1 ? listSize : fromIndex + splitSize;
            int toIndex = Math.min(listSize, fromIndex + splitSize);
            result.add(list.subList(fromIndex, toIndex));
        }
        return result;
    }


    public static <T> List<List<T>> splitList2(List<T> list, int splitSize) {
        int pageSize = (list.size() + splitSize - 1) / splitSize;
        List<List<T>> pageList = new ArrayList<>(pageSize);
        for (int i = 0; i < pageSize; i++) {
            pageList.add(list.stream().skip(i * splitSize).limit(splitSize).collect(Collectors.toList()));
        }
        return pageList;
    }

    public static <T> List<List<T>> splitList3(List<T> list, int splitSize) {
        int pageSize = (list.size() + splitSize - 1) / splitSize;
        List<List<T>> collect = Stream.iterate(0, n -> n + 1).limit(pageSize).parallel()
                .map(i -> list.stream().skip(i * splitSize).limit(splitSize).parallel().collect(Collectors.toList()))
                .collect(Collectors.toList());

        return collect;
    }

    /**
     * 获取List 的最后一个元素
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getLast(List<T> list) {
        if (ObjectUtils.isEmpty(list)) {
            return null;
        }

        return list.get(list.size() - 1);
    }

    /**
     * 获取第一个元素
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getFirst(List<T> list) {
        if (ObjectUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }


    /**
     * 内存分页， 获取某一页的记录数
     *
     * @param allData
     * @param pageInput
     * @param <T>
     * @return
     */
    public static <T> List<T> getOnePageData(List<T> allData, PageInputVo pageInput) {
        if (ObjectUtils.isEmpty(allData)) {
            return allData;
        }
        int totalRecords = allData.size();  // 总数量
        // 总页数
        int totalPages = totalRecords % pageInput.getPageSize() == 0
                ? totalRecords / pageInput.getPageSize() : totalRecords / pageInput.getPageSize() + 1;

        // 保证pageNum 是合理的值
        int pageNum = Math.min(totalPages, Math.max(1, pageInput.getPageNum()));

        int start = (pageNum - 1) * pageInput.getPageSize();
        int end = Math.min(totalRecords, pageNum * pageInput.getPageSize());

        return allData.subList(start, end);
    }

    /**
     * @param allData
     * @param pageNum
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> List<T> getOnePageData(List<T> allData, int pageNum, int pageSize) {
        int start = Math.max(0, pageNum - 1) * pageSize;
        int end = Math.min(allData.size(), pageNum * pageSize);
        return allData.subList(start, end);
    }

    /**
     * @param allData
     * @param pageInput
     * @param <T>
     * @return
     */
    public static <T> List<T> getOnePageData2(List<T> allData, PageInputVo pageInput) {
        int pageNum = pageInput.getPageNum();
        int pageSize = pageInput.getPageSize();
        int totalRows = allData.size();
        int totalPages = (int) Math.ceil(1.0 * totalRows / pageInput.getPageSize());

        int from = (pageNum - 1) * pageSize;
        int to = pageNum == totalPages ? totalRows : from + pageSize;

        return allData.subList(from, to);
    }

}
