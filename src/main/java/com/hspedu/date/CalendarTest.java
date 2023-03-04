package com.hspedu.date;

import com.hspedu.util.CalendarUtil;
import com.mexue.middle.school.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class CalendarTest {
    //如果需要将获得到的时间格式化输出，则需要使用SimpleDateFormart 模式为yyyy-MM-dd HH:mm:ss
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void test1() {
        String[] months = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
        Calendar cal = Calendar.getInstance();
        // 它为特定时间与一组诸如YEAR、MONTH、DAY_OF_MONTH、HOUR等日历字段之间的转换提供了一些方法

        // static int  MONTH . 指示月份的 get 和 set 的字段数字。"这里有非常需要注意的一点,这里显示的月份是由0开始的"
        // 如果只是想要返回的数字的话，只需要将返回的常数+1即可
        int monthIdx = cal.get(Calendar.MONTH);


        log.info("月份序号： {}, 月份：{}", monthIdx, months[monthIdx]);
        log.info("当前时间 d1 = {}, d2={}", cal.getTime(), new Date());
        log.info("当前时间 d1 = {}, d2={}", DateUtil.dateToStrSS(cal.getTime()), DateUtil.dateToStrSS(new Date()));

        log.info("年: {}", cal.get(Calendar.YEAR));//获取当前年份
        log.info("月: {}", cal.get(Calendar.MONTH) + 1); //获取当前月份，需要+1
        log.info("日: {}", cal.get(Calendar.DATE));//获取当前天数，
        log.info("时: {}", cal.get(Calendar.HOUR_OF_DAY));//获取当前处于一天中的第几个小时，
        log.info("分: {}", cal.get(Calendar.MINUTE));//获取当前分钟数
        log.info("秒: {}", cal.get(Calendar.SECOND));//获取当前秒数

        log.info("天数: {}", cal.get(Calendar.DATE));//获取当前天数，
        log.info("最大天数: {}", cal.getActualMaximum(Calendar.DATE));//当前月最大天数
        log.info("最小天数: {}", cal.getActualMinimum(Calendar.DATE));//当前月最小天数
        log.info("最小月数: {}", cal.getActualMaximum(Calendar.MONTH));
        log.info("最大月数: {}", cal.getActualMinimum(Calendar.MONTH));

        log.info("星期 {}", cal.get(Calendar.DAY_OF_WEEK)); // 星期 (Locale.ENGLISH 情况下，周日是1,周一2，周二3，周三4，周四5，周五6，周六7)

        Date date = cal.getTime();
        System.out.println("格式化时间：" + sdf.format(date));
    }

    @Test
    public void calc() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.DATE, cal1.getActualMinimum(Calendar.DATE));
        cal1.set(Calendar.HOUR_OF_DAY, cal1.getActualMinimum(Calendar.HOUR_OF_DAY));
        log.info("当前月的最小时间 = {} 最小的小时 {}", DateUtil.dateToStrSS(cal1.getTime()), cal1.getActualMinimum(Calendar.HOUR_OF_DAY));

        cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
        cal1.set(Calendar.HOUR_OF_DAY, cal1.getActualMaximum(Calendar.HOUR_OF_DAY));
        log.info("当前月的最大时间 = {} 最大的小时 {}", DateUtil.dateToStrSS(cal1.getTime()), cal1.getActualMaximum(Calendar.HOUR_OF_DAY));


        cal1.set(Calendar.DATE, cal1.getActualMinimum(Calendar.DATE) - 1);
        cal1.set(Calendar.HOUR_OF_DAY, cal1.getActualMinimum(Calendar.HOUR_OF_DAY));
        log.info("上个月的最大时间 = {}", DateUtil.dateToStrSS(cal1.getTime()));

        cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
        cal1.set(Calendar.HOUR_OF_DAY, cal1.getActualMaximum(Calendar.HOUR_OF_DAY) + 1);
        log.info("下个月的最小时间 = {}", DateUtil.dateToStrSS(cal1.getTime()));

    }

    // Calendar 的月份是从 0 开始的，但日期和年份是从 1 开始的
    // 可见，将日期设为0以后，月份变成了上个月，但月份可以为0

    @Test
    public void calc2() {
        Calendar cal1 = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c1.set(2017, 1, 1);
        System.out.println(DateUtil.dateToStrSS(c1.getTime()) + ", " + (c1.get(Calendar.YEAR) + "-" + c1.get(Calendar.MONTH) + "-" + c1.get(Calendar.DATE)));
        c1.set(2017, 1, 0);
        System.out.println(DateUtil.dateToStrSS(c1.getTime()) + ", " + (c1.get(Calendar.YEAR) + "-" + c1.get(Calendar.MONTH) + "-" + c1.get(Calendar.DATE)));

        //需求1  3年前的今天
        //根据日历的规则，将指定的时间量添加或减去给定的日历字段
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.YEAR, -3);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; //月份是从零开始的
        int date = calendar.get(Calendar.DATE);
        System.out.print(year + "年 " + month + "月 " + date + "日 ");//2018年 11月 30日
        System.out.println(DateUtil.dateToStrSS(calendar.getTime()));
    }


    @Test
    public void weekAndDayTest() {
        Calendar instance = Calendar.getInstance();
        System.out.println("WEEK_OF_YEAR:" + instance.get(Calendar.WEEK_OF_YEAR));
        System.out.println("一年有" + instance.getActualMaximum(Calendar.WEEK_OF_YEAR) + "个周\n");

        System.out.println(instance.get(Calendar.DATE));
        System.out.println("DAY_OF_MONTH:" + instance.get(Calendar.DAY_OF_MONTH));
        System.out.println("当前月有" + instance.getActualMaximum(Calendar.DAY_OF_MONTH) + "天");
        System.out.println("DAY_OF_YEAR:" + instance.get(Calendar.DAY_OF_YEAR));
        System.out.println("当前年份有" + instance.getActualMaximum(Calendar.DAY_OF_YEAR) + "天");
        System.out.println("指示一个星期中的某天。" + instance.get(Calendar.DAY_OF_WEEK));
    }

    @Test
    public void utilTest() {
        log.info("前一个月  开始时间: {}, 结束时间: {}", CalendarUtil.getPrevDateBegin(), CalendarUtil.getPrevDateEnd());
        log.info("当前一个月  开始时间: {}, 结束时间: {}", CalendarUtil.getDateBegin(), CalendarUtil.getDateEnd());
    }

}
