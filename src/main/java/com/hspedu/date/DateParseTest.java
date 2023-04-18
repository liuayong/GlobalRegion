package com.hspedu.date;

import com.mexue.middle.school.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ————————————————
 * 版权声明：本文为CSDN博主「灵颖桥人」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_22076345/article/details/115213158
 */
@Slf4j
public class DateParseTest {


    private static ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    private static ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            int useExtendPlugin = 1;
            if (useExtendPlugin == 0) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                // 支持解析多种日期格式的 Dateformater
                return new CustomDateFormater();
            }
        }
    };

    // private static final String regex = "\\/|\\.|年|月"; // "\\/|\\.|年|月|日";
    private static final String regex = "[/.年月]";       // " |\\/|\\.|年|月|日";

    public static void main(String[] args) {
        String s1 = "2023-3-29 17:05:40";
        System.out.println(parseDate(s1));
        String s2 = "2023年3月29日 17:05:49";
        System.out.println(parseDate(s2));
        String s3 = "2018/10/3";
        System.out.println(parseDate(s3));
        String s4 = "2018年10月4日";
        System.out.println(parseDate(s4));

        // String s5 = "2018 10 4";
        // System.out.println(parseDate(s5));
    }

    public static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        Date date = null;
        try {
            String dateStrReplace = dateStr.replaceAll(regex, "-");
            // dateStrReplace = dateStrReplace.replaceAll("(\\d{1,2})日\\s*", "$1 ");
            dateStrReplace = dateStrReplace.replaceAll("(?<=\\d{1,2})日\\s*", " ").trim();
            log.info("dateStr = {}, dateStrReplace = {} ", dateStr, dateStrReplace);
            date = SIMPLE_DATE_FORMAT2.get().parse(dateStrReplace);
            log.info("date = {}", DateUtil.dateToStr(date, DateUtil.PNYYYYMMDDHHMMSS));
            return date;
        } catch (ParseException e) {
            // log.error("e = {0}", e);
            // e.printStackTrace();
        }
        return null;
    }
}
