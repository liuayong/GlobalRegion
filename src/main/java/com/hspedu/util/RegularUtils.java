package com.hspedu.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/5
 **/


public class RegularUtils {
    private static Pattern namedGroupCompile = Pattern.compile("\\(\\?<([a-zA-Z][a-zA-Z0-9]*)>");
    
    
    public static JSONObject matchByGroupName(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        JSONObject jsonObject = new JSONObject(true);
        Set<String> namedGroupCandidates = getNamedGroupCandidates(regex);
        List<String> collect = namedGroupCandidates.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        if (matcher.find()) {
            for (String groupName : collect) {
                jsonObject.put(groupName, matcher.group(groupName));
            }
            return jsonObject;
        } else {
            return jsonObject;
        }
    }
    
    /**
     * 命名捕获组
     * 正则编写好，可以直接匹配到需要的内容，不用多处理
     *
     * @param regex
     * @param content
     * @param group
     * @return
     */
    public static String matchStr(String content, String regex, String group) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(group);
        }
        return null;
    }
    
    /**
     * 获取正则表达式对应的命名捕获组（name capture）
     * 输入："(?<year>\\\\d{4})-(?<month>\\\\d{2})-(?<day>\\\\d{2}))"
     * 输出：[year,month,day]
     *
     * @param regex
     * @return
     */
    private static Set<String> getNamedGroupCandidates(String regex) {
        Set<String> namedGroups = new TreeSet<String>();
        Matcher m = namedGroupCompile.matcher(regex);
        while (m.find()) {
            namedGroups.add(m.group(1));
        }
        return namedGroups;
    }
    
    public static void main(String[] args) {
        String email = "11阿发2021-12-31阿萨达发撒";
        String regex = "(\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})";
        JSONObject jsonObject = matchByGroupName(email, regex);
        System.out.println("jsonObject = " + jsonObject);
        System.out.println(JSON.toJSONString(jsonObject));
        System.out.println(jsonObject.get("year"));
        System.out.println(jsonObject.get("month"));
        System.out.println(jsonObject.get("day"));
    }
}