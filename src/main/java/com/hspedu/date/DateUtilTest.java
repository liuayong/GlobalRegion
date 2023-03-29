package com.hspedu.date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {
    // private static final String regex = "\\/|\\.|年|月|日";
    private static final String regex = "[ /.年月日]";

    private static ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));


    public static String replaceToDateStr(String dateStr) {
        if (ObjectUtils.isEmpty(dateStr)) {
            return null;
        }

        // Pattern pattern = Pattern.compile(regex);
        // Matcher matcher = pattern.matcher(dateStr);

        return dateStr.replaceAll(regex, "-");
    }

    /**
     * @param inputDate 要解析的字符串
     * @param patterns  可能出现的日期格式
     * @return 解析出来的日期，如果没有匹配的返回null
     */
    public static Date parseDate(String inputDate, String[] patterns) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat();
        for (String pattern : patterns) {
            df.applyPattern(pattern);
            df.setLenient(false);//设置解析日期格式是否严格解析日期
            ParsePosition pos = new ParsePosition(0);
            Date date = df.parse(inputDate, pos);

            // Date date = df.parse(inputDate);
            if (date != null) {
                return date;
            }
        }
        return null;
    }


    @Test
    public void test1() throws ParseException {
        String[] possiblePatterns =
                {
                        "yyyy-MM-dd",
                        "yyyy-MM-dd HH:mm:ss",
                        "yyyyMMdd",
                        "yyyy/MM/dd",
                        "yyyy年MM月dd日",
                        "yyyy MM dd"
                };

        String inputDate1 = "2018-01-01";
        String inputDate2 = "2018-01-01 12:12:12";
        String inputDate3 = "20180101";
        String inputDate4 = "2018/01/01";
        String inputDate5 = "2018年01月01日";
        String inputDate6 = "2018 01 01";
        System.out.println(parseDate(inputDate6, possiblePatterns));
        System.out.println(parseDate(inputDate1, possiblePatterns));
        System.out.println(parseDate(inputDate2, possiblePatterns));
        System.out.println(parseDate(inputDate3, possiblePatterns));
        System.out.println(parseDate(inputDate4, possiblePatterns));
        System.out.println(parseDate(inputDate5, possiblePatterns));
        System.out.println(parseDate(inputDate6, possiblePatterns));

        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println(DateUtils.parseDate(inputDate6, possiblePatterns));
    }

}