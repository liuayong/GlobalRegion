package com.hspedu.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
            int toIndex = i == listSize - 1 ? listSize : fromIndex + splitSize;
            result.add(list.subList(fromIndex, toIndex));
        }
        return result;
    }
}
