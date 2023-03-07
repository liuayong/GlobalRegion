package com.hspedu.util;

import com.google.common.base.Joiner;
import com.hspedu.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/5
 **/
@Slf4j
public class CommaListUtil {
    private static final String SEPARATOR = ",";
    
    
    /**
     * 转为逗号分隔的字符串
     *
     * @param userList
     */
    public static String toCommaStr(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append("'").append(user.getName()).append("'").append(SEPARATOR);
        }
        
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - SEPARATOR.length());
        }
        return sb.toString();
    }
    
    /**
     * 使用stream()方法调用列表对象流
     * 使用map方法和lambda表达式对每个对象进行迭代
     * 在lambda表达式中获取对象姓名
     * java.util.stream.Collectors 连接方法使用逗号分隔符，并使用还原器返回逗号分隔符的字符串。collect
     * <p>
     * 作者：迪鲁宾
     * 链接：https://juejin.cn/post/7127510321849171982
     * 来源：稀土掘金
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param userList
     * @return
     */
    public static String toCommaStr2(List<User> userList) {
        //return userList.stream().map(User::getName).collect(Collectors.joining(","));
        return userList.stream().map(e -> "'" + e.getName() + "'").collect(Collectors.joining(","));
    }
    
    public static String toCommaStr3(List<User> userList) {
        List<String> userNames = userList.stream().map(User::getName).collect(Collectors.toList());
        return org.apache.commons.lang3.StringUtils.join(userNames, ",");
    }
    
    public static String toCommaStr4(List<User> userList) {
        List<String> userNames = userList.stream().map(User::getName).collect(Collectors.toList());
        return org.springframework.util.StringUtils.arrayToDelimitedString(userNames.toArray(), ",");
    }
    
    /**
     * 我们使用String.join()函数，给函数传递一个分隔符合一个迭代器，一个StringJoiner对象会帮助我们完成所有的事情
     *
     * @param userList
     * @return
     */
    public static String toCommaStr5(List<User> userList) {
        List<String> userNames = userList.stream().map(User::getName).collect(Collectors.toList());
        return String.join(",", userNames);
    }
    
    public static String toCommaStr6(List<User> userList) {
        List<String> userNames = userList.stream().map(User::getName).collect(Collectors.toList());
        return Joiner.on(",").join(userNames);
    }
    
    //++++++++++++++++++++++++++++++++ 接下来是逗号字符串转换为List   ++++++++++++++++++++++
    
    /**
     * 将逗号分隔的字符串转换为List
     *
     * @param commaStr
     * @return
     */
    public static List<String> toList(String commaStr) {
        List<String> result = Arrays.asList(commaStr.split(","));
        return result;
    }
    
    public static List<String> toList2(String commaStr) {
        return null;
    }
    
    
    
    
    // 逗号分隔字符串
    @Test
    public void test1() {
        List<User> userList = User.getUserList();
        log.info("转逗号字符串toCommaStr  = {}", CommaListUtil.toCommaStr(userList));
        log.info("转逗号字符串toCommaStr2 = {}", CommaListUtil.toCommaStr2(userList));
        log.info("转逗号字符串toCommaStr3 = {}", CommaListUtil.toCommaStr3(userList));
        log.info("转逗号字符串toCommaStr4 = {}", CommaListUtil.toCommaStr4(userList));
        log.info("转逗号字符串toCommaStr5 = {}", CommaListUtil.toCommaStr5(userList));
        log.info("转逗号字符串toCommaStr6 = {}", CommaListUtil.toCommaStr6(userList));
    }
    
    
}
