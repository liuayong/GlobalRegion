package com.hspedu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类: https://blog.51cto.com/u_14479502/3115721
 */
public class CalendarUtil {

    //获取当前系统时间的前一个月的1号
    public static String getPrevDateBegin() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);//月份为0-11
        c.set(Calendar.DATE, 1);//设置当前时间的前一个月1日
        Date date = c.getTime();
        String dateStr = sdf.format(date) + " 00:00:00";
        return dateStr;
    }

    //获取当前时间的前一个月的月底
    public static String getPrevDateEnd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);//月份为0-11
        c.set(Calendar.DATE, 1);
        int maxdate = c.getActualMaximum(Calendar.DATE);//获取当月的最大天数
        c.set(Calendar.DATE, maxdate);
        Date date = c.getTime();
        String dateStr = sdf.format(date) + " 23:59:59";
        return dateStr;
    }


    //获取当前系统时间的的1号
    public static String getDateBegin() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);//月份为0-11
        c.set(Calendar.DATE, 1);//设置当前时间的前一个月1日
        Date date = c.getTime();
        String dateStr = sdf.format(date) + " 00:00:00";
        return dateStr;
    }

    //获取当前时间的的月底
    public static String getDateEnd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);//月份为0-11
        c.set(Calendar.DATE, 1);
        int maxdate = c.getActualMaximum(Calendar.DATE);//获取当月的最大天数
        c.set(Calendar.DATE, maxdate);
        Date date = c.getTime();
        String dateStr = sdf.format(date) + " 23:59:59";
        return dateStr;
    }


}
