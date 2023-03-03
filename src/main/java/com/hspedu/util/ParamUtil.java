package com.hspedu.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: GlobalRegion
 * @Description: 参数工具类
 * @Author: Administrator
 * @Create: 2023-03-04
 **/
@Slf4j
public class ParamUtil {
    
    public static void main(String[] args) {
        List<Long> paramsList = new ArrayList<Long>();
        for (int i = 0; i < 24; i++) {
            paramsList.add(Long.valueOf(i));
        }
        System.out.println(splitParams("sample_id", 6, paramsList));
        System.out.println(splitParams2("sample_id", 6, paramsList));
    }
    
    public static String splitParams(String column, int splitSize, List<Long> paramsList) {
        int listSize = paramsList.size();
        int pageNum = listSize % splitSize == 0 ? listSize / splitSize : listSize / splitSize + 1;  // ceil
        log.info("list条数={}, splitSize={}, pageNum页数={}", listSize, splitSize, pageNum);
        List<List<Long>> splitList = ListUtil.splitList(paramsList, splitSize);
        StringBuilder sb = new StringBuilder();
        for (List<Long> oneList : splitList) {
            sb.append(column).append(" IN (").append(StreamUtil.listToString(oneList)).append(") OR ");
        }
        //return "( " + sb.substring(0, sb.length() - 4) + " )";
        sb.delete(sb.length() - 4, sb.length()).insert(0, "( ").append(" )");
        return sb.toString();
    }
    
    
    public static String splitParams2(String column, int splitSize, List<Long> paramsList) {
        int listSize = paramsList.size();
        int pageNum = listSize % splitSize == 0 ? listSize / splitSize : listSize / splitSize + 1;
        log.info("list条数={}, splitSize={}, pageNum页数={}", listSize, splitSize, pageNum);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pageNum; i++) {
            List<Long> subList;
            if (i == pageNum - 1) {    // 最后一页特殊处理
                subList = paramsList.subList(i * splitSize, listSize);
            } else {
                subList = paramsList.subList(i * splitSize, (i + 1) * splitSize);
            }
            sb.append(column).append(" IN (").append(StreamUtil.listToString(subList)).append(") OR ");
        }
        sb.delete(sb.length() - 4, sb.length()).insert(0, "( ").append(" )");
        return sb.toString();
    }
    
    
}
