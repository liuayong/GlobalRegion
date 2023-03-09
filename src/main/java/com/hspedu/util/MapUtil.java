package com.hspedu.util;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {


    /**
     * 合并两个Map
     *
     * @param map1
     * @param map2
     * @return
     */
    @NotNull
    public static Map<String, Object> mergeMap(Map<String, Object> map1, Map<String, Object> map2) {
        // 合并
        Map<String, Object> combineResultMap = new HashMap<>();
        combineResultMap.putAll(map1);
        combineResultMap.putAll(map2);
        return combineResultMap;
    }

    public static Map<String, String> mergeMap2(Map<String, String> map1, Map<String, String> map2) {
        // 合并
        Map<String, String> combineResultMap = new HashMap<>();
        combineResultMap.putAll(map1);
        combineResultMap.putAll(map2);
        return combineResultMap;
    }
}
