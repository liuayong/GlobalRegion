package com.byd;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SqlUtil {
    
    private static String regex = "(\\w[^,(]*?)\\((String|Integer|Long|Date|Timestamp)\\)|null";
    private static Pattern pattern = Pattern.compile(regex);
    
    @Test
    public void printSql() {
        String sql = "==>  Preparing: SELECT i.ID id, i.REF_ID refId, i.PERIOD_MARK periodMark, i.PERIOD period, i.PERIOD_START periodStart, i.PERIOD_END periodEnd, i.DEMAND_NUM demandNum, i.REPLAY_NUM replayNum, i.REPLAY_MEMO replayMemo FROM DO_I_D_ITEM i where i.REF_ID in ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) AND i.period_start >= ? AND i.period_end <= ? order by i.REF_ID,i.PERIOD_START\n" +
                "==> Parameters: 1369877868642304(Long), 1369877868593152(Long), 1369877868576768(Long), 1369877868568576(Long), 1369877684584448(Long), 1369877684576256(Long), 1369877684568064(Long), 1369877684518912(Long), 1369877684502528(Long), 1369877668298752(Long), 1369877668282368(Long), 1369876663795712(Long), 1369876663787521(Long), 1369876663746560(Long), 1369876663738368(Long), 1369876663721984(Long), 1369863873052672(Long), 1369863873028096(Long), 1369863872978969(Long), 1369863872978944(Long), 1369863872970777(Long), 1369863872970752(Long), 1369863872954368(Long), 1369863872946176(Long), 1369863872880640(Long), 1369863872864256(Long), 1369863872856064(Long), 1369863872815104(Long), 1369863872806912(Long), 1369863872782336(Long), 1369863872692224(Long), 1369863872651264(Long), 1369863748861952(Long), 1369863748853761(Long), 1369863748829184(Long), 1369863748788224(Long), 1369863748780032(Long), 1369863748771840(Long), 1369863748763648(Long), 1369863748755456(Long), 1369863748739072(Long), 2022-01-01 00:00:00.0(Timestamp), 2021-12-31 23:59:59.05(Timestamp)\n" +
                "<==      Total: 0";
        
        String sql1 = parseSql(sql);
        Assert.assertEquals(sql1.trim(), parseSqlV2(sql).trim());
        System.out.println(sql1);
        
        
    }
    
    
    public static String parseSqlV2(String sql) {
        sql = sql.replaceAll("==>", "");
        sql = sql.replaceAll("<==", "");
        int paramPos = sql.indexOf("Parameters:");
        String matchSql = sql.substring(sql.indexOf("Preparing:") + 11, paramPos - 1);
        String sqlParamsStr = sql.substring(paramPos + "Parameters:".length());
        
        // Sql参数进行正则匹配
        Matcher m1 = pattern.matcher(sqlParamsStr);
        int idx = 0;
        List<String[]> params = new ArrayList<>();
        while (m1.find()) {
            String value = m1.group(1);
            String type = m1.group(2);
            params.add(new String[]{value, type});
            ++idx;
        }
        log.info("[parseSql2] idx = " + idx);
        
        // Sql参数进行正则匹配
        String regex2 = "\\?"; // "=\\s*\\?";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher m2 = pattern2.matcher(matchSql);
        // SQL语句进行正则匹配
        idx = 0;
        StringBuffer sb = new StringBuffer();
        while (m2.find()) {
            m2.appendReplacement(sb, getValue(params.get(idx++)));
        }
        m2.appendTail(sb);
        
        // 返回的SQL不能含有 ？
        Assert.assertFalse("含有?占位符: sql解析不完全", sb.toString().contains("?"));
        
        return sb.toString().trim();
    }
    
    /**
     * 解析SQL
     *
     * @param sql
     * @return
     */
    public static String parseSql(String sql) {
        
        sql = sql.replaceAll("==>", "");
        sql = sql.replaceAll("<==", "");
        int paramPos = sql.indexOf("Parameters:");
        String matchSql = sql.substring(sql.indexOf("Preparing:") + 11, paramPos - 1);
        String sqlParamsStr = sql.substring(paramPos + "Parameters:".length());
        
        
        //String regex = "([\\w.].+?)\\((String|Integer|Date|Timestamp)\\)|null";
        List<String[]> params = new ArrayList<>(StringUtils.countOccurrencesOf(matchSql, "?"));
        
        // 现在创建 matcher 对象
        Matcher m = pattern.matcher(sqlParamsStr);
        int count = 0;
        while (m.find()) {
            count++;
            String value = m.group(1);
            String type = m.group(2);
            params.add(new String[]{value, type});
        }
        log.info("[parseSql] count = " + count);
        //params.forEach(e -> System.out.println(Arrays.toString(e)));
        
        for (String[] param : params) {
            String value = getValue(param);
            matchSql = matchSql.replaceFirst("\\?", value);
        }
        
        // 返回的SQL不能含有 ？
        Assert.assertFalse("含有?占位符: sql解析不完全", matchSql.contains("?"));
    
        return matchSql.trim();
    }
    
    /**
     * 根据 [value:type]数组 获取对应的value值
     *
     * @param param
     * @return
     */
    private static String getValue(String[] param) {
        String value = param[0];
        String type = param[1];
        if ("String".equals(type) || "Date".equals(type)) {
            value = "'" + value + "'";
        } else if ("Timestamp".equals(type)) {
            //value = "to_timestamp('" + value + "', 'YYYY-MM-DD HH24:MI:SS.ff')";
            value = "'" + value.replaceAll("\\.\\d+", "") + "'";
        }
        
        if (value == null && type == null) {
            value = "null";
        }
        return value;
    }
    
    
}
