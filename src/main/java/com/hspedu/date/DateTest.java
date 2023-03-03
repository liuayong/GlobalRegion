package com.hspedu.date;

import com.mexue.middle.school.util.DateUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTest {
    
    @Test
    public void groupByMonth() {
        int[] nums = {10, 35};
        
        String[] date1 = {"2023-2-21", "2023-2-25"}; //  -->2023-02分得:  10  未跨月
        String[] date2 = {"2023-2-26", "2023-03-4"}; //  --> 2023-02分得: 35/7 *3(2月份占3天), 2023-03分得:  35/7 * 4（3月份占4天）
        // (一共7天， 按照跨月比例分配)
        // 如果有跨月跨了几个月， 平均按天分配到每个月上， 这里跨了两个月
        
        
        for (int i = 0; i < nums.length; i++) {
            System.out.println(DateUtil.strToDate(date1[i], DateUtil.PNYYYYMMDD) + " , " + DateUtil.strToDate(date2[i], DateUtil.PNYYYYMMDD));
        }
        
        // 两个日期相隔的天数
        Date startDate = DateUtil.strToDate(date2[0], DateUtil.PNYYYYMMDD);
        Date endDate = DateUtil.strToDate(date2[1], DateUtil.PNYYYYMMDD);
        Integer diffDays = DateUtil.betweenDateDD(startDate, endDate);
        System.out.println(diffDays);
        
        Period period = Period.between(toLocalDate(startDate), toLocalDate(endDate));
        long daysDiff = Math.abs(period.getDays());
        System.out.println("相差天数: " + daysDiff);
        
        // 3.开始时间段区间集合
        List<Long> beginDateList = new ArrayList<Long>();
        // 4.结束时间段区间集合
        List<Long> endDateList = new ArrayList<Long>();
        // 5.调用工具类
        getIntervalTimeByMonth(startDate.getTime(), endDate.getTime(), beginDateList, endDateList);
        // 6.打印输出
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < beginDateList.size(); i++) {
            Long beginStr = beginDateList.get(i);
            Long endStr = endDateList.get(i);
            String begin1 = sdf.format(new Date(beginStr));
            String end1 = sdf.format(new Date(endStr));
            System.out.println("第" + i + "段时间区间:" + begin1 + "-------" + end1);
        }
        
    }
    
    public static LocalDate toLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }
    
    @Test
    public void timeSplitByMonth() {
        // 1.开始时间 2017-02-03 13:16:04
        Long startTime = 1486098964000L;
        // 2.结束时间 2019-07-03 13:16:05
        Long endTime = 1562130965000L;
        // 3.开始时间段区间集合
        List<Long> beginDateList = new ArrayList<Long>();
        // 4.结束时间段区间集合
        List<Long> endDateList = new ArrayList<Long>();
        // 5.调用工具类
        getIntervalTimeByMonth(startTime, endTime, beginDateList, endDateList);
        // 6.打印输出
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < beginDateList.size(); i++) {
            Long beginStr = beginDateList.get(i);
            Long endStr = endDateList.get(i);
            String begin1 = sdf.format(new Date(beginStr));
            String end1 = sdf.format(new Date(endStr));
            System.out.println("第" + i + "段时间区间:" + begin1 + "-------" + end1);
        }
    }
    
    /**
     * 按照月份分割一段时间
     *
     * @param startTime     开始时间戳(毫秒)
     * @param endTime       结束时间戳(毫秒)
     * @param beginDateList 开始段时间戳 和 结束段时间戳 一一对应
     * @param endDateList   结束段时间戳 和 开始段时间戳 一一对应
     */
    public static void getIntervalTimeByMonth(Long startTime, Long endTime, List<Long> beginDateList, List<Long> endDateList) {
        Date startDate = new Date(startTime);
        Date endDate = new Date(endTime);
        System.out.println("start: " + DateUtil.dateToStrSS(startDate));
        System.out.println("end: " + DateUtil.dateToStrSS(endDate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        beginDateList.add(calendar.getTimeInMillis());
        while (calendar.getTimeInMillis() < endDate.getTime()) {
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            if (calendar.getTimeInMillis() < endDate.getTime()) {
                endDateList.add(calendar.getTimeInMillis());
            } else {
                endDateList.add(endDate.getTime());
                break;
            }
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            beginDateList.add(calendar.getTimeInMillis());
        }
    }
    
    
    String[] date2 = {"2023-2-26", "2023-03-4"}; //  --> 2023-02分得: 35/7 *3(2月份占3天), 2023-03分得:  35/7 * 4（3月份占4天）
    
    
    @Test
    public void testIntervalTimeByMonth() {
        Date startDate = DateUtil.strToDate("2023-1-26 12:13:15", "yyyy-MM-dd HH:mm:ss");
        Date endDate = DateUtil.strToDate("2023-5-4 22:21:23", "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        
        //System.out.println(calendar.getTime());
        //System.out.print("年: " + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.MONTH) + " ");
        //System.out.println(calendar.get(Calendar.YEAR) * 100 + calendar.get(Calendar.MONTH));
        
        
        // 5.调用工具类
        List<DatePeriodVo> periodList = getIntervalTimeByMonth(startDate, endDate);
        System.out.println(periodList.size());
        for (DatePeriodVo datePeriodVo : periodList) {
            System.out.println(datePeriodVo);
        }
    }
    
    public static List<DatePeriodVo> getIntervalTimeByMonth(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        
        int idx = 0;
        List<DatePeriodVo> datePeriodList = new ArrayList<>();
        int peroid = calendar.get(Calendar.YEAR) * 100 + calendar.get(Calendar.MONTH);
        
        while (calendar.getTimeInMillis() < endDate.getTime()) {
            DatePeriodVo datePeriodVo = new DatePeriodVo(peroid, calendar.getTime(), idx++);
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            if (calendar.getTimeInMillis() < endDate.getTime()) {
                datePeriodVo.setPeriodEnd(calendar.getTime());
                datePeriodList.add(datePeriodVo);
            } else {
                datePeriodVo.setPeriodEnd(endDate);
                datePeriodList.add(datePeriodVo);
                break;
            }
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        }
        return datePeriodList;
    }
    
    
    /**
     * 2个日期之间相关的天数（输入的参数要求：yyyy-MM-dd）
     *
     * @param target 结束日期
     * @param source 开始日期
     * @return
     */
    public static Long intervalDays(String source, String target) {
        LocalDate localDate1 = LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate localDate2 = LocalDate.parse(target, DateTimeFormatter.ISO_LOCAL_DATE);
        return intervalDays(localDate1, localDate2);
    }
    
    /**
     * 2个日期之间相关的天数
     *
     * @param target 结束日期
     * @param source 开始日期
     * @return
     */
    public static Long intervalDays(LocalDate source, LocalDate target) {
        return ChronoUnit.DAYS.between(source, target);
    }
}
