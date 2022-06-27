package com.mexue.middle.school.util;

import org.springframework.util.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Date相关处理
 */
public class DateUtil {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public final static String PNYYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm
     */
    public final static String PNYYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd HH
     */
    public final static String PNYYYYMMDDHH = "yyyy-MM-dd HH";
    /**
     * yyyy-MM-dd
     */
    public final static String PNYYYYMMDD = "yyyy-MM-dd";
    /**
     * yyyy-MM
     */
    public final static String PNYYYYMM = "yyyy-MM";
    /**
     * yyyy
     */
    public final static String PNYYYY = "yyyy";
    /**
     * HH
     */
    public final static String HH = "HH";
    /**
     * HH:mm:ss
     */
    public final static String PNHHMMSS = "HH:mm:ss";
    /**
     * HH:mm
     */
    public final static String PNHHMM = "HH:mm";

    /**
     * 日期格式日期转字符串格式日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToStr(Date date, String pattern) {
        if (isEmpty(date) || isEmpty(pattern)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 日期格式日期转字符串格式日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToStr(Date date, String pattern, Locale locale) {
        if (isEmpty(date) || isEmpty(pattern)) {
            return null;
        }
        if (null == locale) {
            locale = Locale.CHINESE;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
        return format.format(date);
    }

    public static String timeStampToDateStr(long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = new Long(timeStamp);
        String d = format.format(time);
        return d;
    }

    public static Date timeStampToDate(long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(timeStampToDateStr(timeStamp));
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * 日期格式转字符串格式，精确到天
     * 默认格式 2014-12-11
     *
     * @param date
     * @return
     */
    public static String dateToStrDD(Date date) {
        return dateToStr(date, PNYYYYMMDD);
    }

    /**
     * 日期格式转字符串格式，精确到秒
     * 默认格式 2014-12-11 23:45:22
     *
     * @param date
     * @return
     */
    public static String dateToStrSS(Date date) {
        return dateToStr(date, PNYYYYMMDDHHMMSS);
    }

    /**
     * 字符串格式日期转日期格式日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date strToDate(String date, String pattern) {
        if (isEmpty(date) || isEmpty(pattern)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串日期转换成Date日期（支持[PM,AM][上午,下午]）
     *
     * @param date
     * @return
     */
    public static Date strToDate(String date) {
        date = resetDateStr(date);
        if (null != date) {
            String dateTimeStr = date.split(" +").length > 1 ? date.split(" +")[1] : "";
            return strToDate(date, checkPN(dateTimeStr));
        }
        return null;
    }

    /**
     * 转化处理日期字符串
     *
     * @param date
     * @return
     */
    private static String resetDateStr(String date) {
        if (StringUtils.hasText(date)) {
            date = date.trim();
            if (date.replaceAll("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{1,3}$", "").length() == 0) {
                date = date.replaceAll(".\\d{1,3}$", "");
            } else if (date.replaceAll("[a-zA-Z ]", "").replaceAll("^\\d{4}:\\d{2}:\\d{6}$", "").length() == 0) {
                date = eDateNumToADateNum(date);
            } else {
                date = treatTime(cDateNumToADateNum(date));
            }
            if (date.replaceAll("^\\d{4}-\\d{2}-\\d{2}( \\d{2}){0,1}(:\\d{2}){0,1}(:\\d{2}){0,1}$", "").length() == 0) {
                return date;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 检查日期字符串是否有误
     *
     * @param date
     * @return
     */
    public static boolean isDateStrErr(String date) {
        date = resetDateStr(date);
        return null == date;
    }

    /**
     * 获得当前日期指定时间点的date对象
     * 例如：想要获得今天9:00 的日期对象 dateSpliceTime("9:00")
     *
     * @param timeStr 时分秒字符串
     * @return
     */
    public static Date getDateByTimeStr(String timeStr) {
        Date date = new Date();
        if (isEmpty(date) || isEmpty(timeStr)) {
            return null;
        }
        String dateStr = dateToStr(date, PNYYYYMMDD) + " " + timeStr.trim();
        return strToDate(dateStr);
    }


    /**
     * 获取指定日期 所在月份的上一个月或下一个月（由传入的month值而定）的第一天的Date对象
     *
     * @param month：0代表当前月 1是代表下一个月，-1代表上一个月 其他以此类推
     * @return
     */
    public static Date monthFirstDay(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateByTimeStr(date, "00:00:00"));
        cal.add(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取指定日期 所在月份的上一个月或下一个月（由传入的month值而定）的最后一天的Date对象
     *
     * @param month：0代表当前月 1是代表下一个月，-1代表上一个月 其他以此类推
     * @return
     */
    public static Date monthLastDay(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateByTimeStr(date, "23:59:59"));
        cal.add(Calendar.MONTH, month + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }


    /**
     * 获得指定日期所在周周一的 date对象
     * 一周为 周一到周日
     *
     * @param date
     * @return
     */
    public static Date dayOfMonday(Date date) {
        int dayOfWeek = dayOfWeek(date);
        Date outDate;
        if (dayOfWeek == 1) {
            outDate = dateGapDays(date, -6);
        } else {
            outDate = dateGapDays(date, -(dayOfWeek - 2));
        }
        return getDateByTimeStr(outDate, "00:00:00");
    }

    /**
     * 获得指定日期所在周周日的 date对象
     * 一周为 周一到周日
     *
     * @param date
     * @return
     */
    public static Date dayOfSunday(Date date) {
        int dayOfWeek = dayOfWeek(date);
        Date outDate;
        if (dayOfWeek == 1) {
            outDate = dateGapDays(date, 0);
        } else {
            outDate = dateGapDays(date, 6 - (dayOfWeek - 2));
        }
        return getDateByTimeStr(outDate, "23:59:59");
    }

    /**
     * 是否周末，周六日算周末
     *
     * @param date
     * @return 范围 1~7,1=星期日 7=星期六，其他类推
     */
    public static boolean isWeekend(Date date) {
        Integer weekNum = dayOfWeek(date);
        if (weekNum.intValue() == 1 || weekNum.intValue() == 7) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取指定日期所在月份的日历嵌套列表，
     * <p>
     * 嵌套规则如下（日历List嵌套星期List）下面范例是吧14年12月份的日历输出成table表格
     * List<List<String>> temp = monthCalendarList(strToDate("2014-12-01")) ;
     * System.out.print("<table> ");
     * for (List<String> tempOne:temp){
     * System.out.print("<tr> ");
     * for (String oneDate:tempOne){
     * System.out.print("<td>");
     * if("5".equals(oneDate)){
     * System.out.print(" "+oneDate+"-");
     * }else if(null!=oneDate) {
     * System.out.print(" "+oneDate);
     * }
     * System.out.print("</td>");
     * }
     * System.out.println("</tr>");
     * }
     * System.out.print("</table> ");
     *
     * @param date
     * @return
     */
    public static List<List<String>> monthCalendarList(Date date) {
        List<List<String>> outCalendarList = new ArrayList<List<String>>();
        int hour = dayOfWeek(monthFirstDay(date, 0));
        int lastDay = Integer.parseInt(dateToStr(monthLastDay(date, 0), "dd"));
        int maxNum = (((hour - 1 + lastDay) + 6) / 7) * 7;
        List<String> rowList = new ArrayList<String>();
        for (int i = 1; i <= maxNum; i++) {
            rowList.add(i < hour || i > hour - 1 + lastDay ? null : Integer.toString(i + 1 - hour));
            if (rowList.size() == 7) {
                outCalendarList.add(rowList);
                rowList = new ArrayList<String>();
            }
        }
        return outCalendarList;
    }

    /**
     * 日期间隔的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer betweenDateDD(Date startDate, Date endDate) {
        startDate = strToDate(dateToStr(startDate, PNYYYYMMDD));
        endDate = strToDate(dateToStr(endDate, PNYYYYMMDD));
        Long timeSSS = null == startDate || null == endDate ? 0 : endDate.getTime() - startDate.getTime();
        timeSSS = timeSSS / 1000 / 60 / 60 / 24;
        return timeSSS.intValue();
    }

    /**
     * 日期的间隔小时数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long betweenDateHH(Date startDate, Date endDate) {
        return betweenDateMin(startDate, endDate) / 60;
    }

    /**
     * 日期的间隔分钟数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long betweenDateMin(Date startDate, Date endDate) {
        return betweenDateSS(startDate, endDate) / 60;
    }

    /**
     * 日期的间隔秒数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long betweenDateSS(Date startDate, Date endDate) {
        return betweenDateSSS(startDate, endDate) / 1000;
    }

    /**
     * 日期的间隔毫秒数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long betweenDateSSS(Date startDate, Date endDate) {
        return null == startDate || null == endDate ? 0 : endDate.getTime() - startDate.getTime();
    }

    /**
     * 获得指定日期的前一天或者后一天（由传入的num决定）的 date 对象 后几天的日期
     *
     * @param date
     * @param num：0表示当前天 -1 表示上一天，1表示后一天，其他以此类推
     * @return
     */
    public static Date dateGapDays(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, num);
        return cal.getTime();
    }

    /**
     * 获取系统当前时间，精确到天
     *
     * @return
     */
    public static Date getNowDD() {
        Date date = new Date();
        String dateStr = dateToStr(date, PNYYYYMMDD);
        return strToDate(dateStr, PNYYYYMMDD);
    }

    /**
     * 获取系统当前时间，精确到天
     *
     * @return
     */
    public static String getNowDDStr() {
        Date date = new Date();
        String dateStr = dateToStr(date, PNYYYYMMDD);
        return dateStr;
    }

    /**
     * 获取系统时间，精确到秒
     *
     * @return
     */
    public static Date getNowSS() {
        Date date = new Date();
        String dateStr = dateToStr(date, PNYYYYMMDDHHMMSS);
        return strToDate(dateStr, PNYYYYMMDDHHMMSS);
    }

    /**
     * 获取指定日期是当年的第几周
     * @param date
     * @return
     */
//	public static Integer getWeekOfYear(Date date) {
//        Calendar calendar=Calendar.getInstance();
//		calendar.setFirstDayOfWeek(Calendar.MONDAY);
//		calendar.setTime(date);
//		return calendar.get(Calendar.WEEK_OF_YEAR);
//	}

    /**
     * 获取指定日期是当年的第几周
     *
     * @param date
     * @return
     */
    public static Integer getWeekOfYear(Date date) {
        Date pointYearDate = dayOfSunday(date);
        Date endDateMonday = dayOfMonday(date);
        Date yearFirstDate = strToDate(dateToStr(pointYearDate, "yyyy") + "-01-01");
        Date beginDate = dayOfMonday(yearFirstDate);
        int betweenWeekNum = betweenDateDD(beginDate, endDateMonday);
        int weekNumOut = betweenWeekNum / 7;
        weekNumOut++;
        return weekNumOut;
    }

    public static String getWeekYear(Date date) {
        Date pointYearDate = dayOfSunday(date);
        return dateToStr(pointYearDate, "yyyy");
    }

    /**
     * 年最大周
     *
     * @param year
     * @return
     */
    public static Integer getMaxWeek(String year) {
        Date yearMaxDate = dateGapDays(dayOfMonday(strToDate((Integer.valueOf(year) + 1) + "-01-01")), -1);
        return getWeekOfYear(yearMaxDate);
    }

    /**
     * 年和周获取周一日期；
     *
     * @param year
     * @param weekNum
     * @return
     */
    public static Date mondayOfYearWeekNum(String year, Integer weekNum) {
        Date yearFirstDate = strToDate(year + "-01-01");
        Date beginDate = dayOfMonday(yearFirstDate);
        String beginDateYear = DateUtil.dateToStr(beginDate, "yyyy");
        Integer gapDays = (weekNum - 1) * 7;
//        if (beginDateYear.equals(year)) {
//            gapDays -= 7;
//        }
        Date outDate = dateGapDays(beginDate, gapDays);
        return outDate;
    }

    /**
     * 年和周获取周日日期；
     *
     * @param year
     * @param weekNum
     * @return
     */
    public static Date sundayOfYearWeekNum(String year, Integer weekNum) {
        Date yearFirstDate = strToDate(year + "-01-01");
        Date beginDate = dayOfMonday(yearFirstDate);
        Integer gapDays = (weekNum - 1) * 7;
        Date outDate = dateGapDays(beginDate, gapDays);
        return dateGapDays(outDate, 6);
    }


    /**
     * 获取传入日期是星期几
     *
     * @param date
     * @return 范围 1~7,1=星期日 7=星期六，其他类推
     */
    public static Integer dayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 范围 1~7,1=星期日 7=星期六，其他类推
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取指定年的指定周的周一
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getMonday(Integer year, Integer week) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        Date outDate = calendar.getTime();
        return getDateByTimeStr(outDate, "00:00:00");
    }

    /**
     * 获取指定年的指定周的周日
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getSunday(Integer year, Integer week) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        Date outDate = calendar.getTime();
        return getDateByTimeStr(outDate, "23:59:59");
    }


    /*************************************************以下内部自用*********************************************/

    /**
     * //TODO 未知的应用场景
     * 传入年月日，获得日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    private static Date madeDate(String year, String month, String day) {
        Date outDate = strToDate(year + "-01-01");
        Integer monthInt = Integer.parseInt(month);
        outDate = monthFirstDay(outDate, monthInt - 1);
        Integer dayInt = Integer.parseInt(day);
        outDate = dateGapDays(outDate, dayInt - 1);
        return outDate;
    }


    /**
     * 检查Map
     *
     * @param map map集合
     * @return
     */
    @SuppressWarnings("unchecked")
    private static boolean isEmpty(Map map) {
        return null == map ? true : map.isEmpty();
    }

    /**
     * 检查List集合
     *
     * @param list
     * @return
     */
    private static boolean isEmpty(List list) {
        return null == list ? true : list.isEmpty();
    }

    /**
     * 检查字符串
     *
     * @param str
     * @return
     */
    private static boolean isEmpty(String str) {
        return null == str ? true : "".equals(str.replaceAll(" +", ""));
    }

    /**
     * 检测Integer
     *
     * @param value
     * @return
     */
    private static boolean isEmpty(Integer value) {
        return null == value ? true : "".equals(value.toString().replaceAll("[ 0]+", ""));
    }

    /**
     * 检测Double
     *
     * @param value
     * @return
     */
    private static boolean isEmpty(Double value) {
        return null == value ? true : "".equals(value.toString().replaceAll("[ .0]+", ""));
    }

    /**
     * 检测Date
     *
     * @param date
     * @return
     */
    private static boolean isEmpty(Date date) {
        return null == date ? true : isEmpty(date.toString());
    }

    /**
     * 检测所有类型对象是否为空
     *
     * @param obj
     * @return
     */
    private static boolean isEmpty(Object obj) {
        return null == obj ? true : isEmpty(obj.toString());
    }


    /************************************************智能日期转换方法(开始)********************************************************/
    /**
     * 根据传入的时间判断日期格式
     *
     * @param timeStr
     * @return
     */
    private static String checkPN(String timeStr) {
        int timeStrLength = timeStr == null ? 0 : timeStr.trim().length();
        return timeStrLength == 2 ? PNYYYYMMDDHH :
                timeStrLength == 5 ? PNYYYYMMDDHHMM :
                        timeStrLength == 8 ? PNYYYYMMDDHHMMSS :
                                PNYYYYMMDD;
    }


    /**
     * 把各种常用字符串日期转换成yyyy-MM-dd的时间字符串
     *
     * @param dateStr
     * @return
     */
    private static String formatYMD(String dateStr) {
        String[] dateArry = dateStr.split("\\D+");
        String outDate = "";
        if (dateArry.length >= 3) {
            for (int i = 0; i < 3; i++) {
                outDate = dateArry[i].length() == 4 ? dateArry[i] + outDate : outDate + "-" + dateArry[i];
            }
        }
        return outDate;
    }

    /**
     * 拆分中文格式的日期，然后重新组合成阿拉伯数值格式的日期
     *
     * @param cDate
     * @return
     */
    private static String cDateNumToADateNum(String cDate) {
        String outADateNum = "";
        String[] cDateArray = cDate.split("[^○一二三四五六七八九十]+");
        String[] otherArray = cDate.split("[○一二三四五六七八九十]+");
        for (int i = 0; (i < cDateArray.length || i < otherArray.length); i++) {
            outADateNum += (i < otherArray.length ? otherArray[i] : "") + (i < cDateArray.length && !isEmpty(cDateArray[i]) ? dateDNumToANum(cDateArray[i]) : "");
        }
        return outADateNum;
    }

    /**
     * 默认字符串日期格式组合成阿拉伯数字格式日期
     *
     * @param cDate
     * @return
     */
    private static String eDateNumToADateNum(String cDate) {
        String outADateNum = cDate;
        String[] englishMonth = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] arabMonth = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        for (int i = 0; (i < englishMonth.length || i < arabMonth.length); i++) {
            outADateNum = outADateNum.replace(englishMonth[i], arabMonth[i]);
        }
        arabMonth = outADateNum.split(" +");
        outADateNum = arabMonth[5] + "-" + arabMonth[1] + "-" + arabMonth[2] + " " + arabMonth[3];
        return outADateNum;
    }

    /**
     * 中文数值日期转换成 阿拉伯数值日期
     *
     * @param cNum
     * @return
     */
    private static String dateDNumToANum(String cNum) {
        if (cNum.length() < 4) {
            return cNumToANum(cNum).split("\\.")[0];
        }
        String[] chinaNum = {"○", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] arabNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < chinaNum.length; i++) {
            cNum = cNum.replaceAll(chinaNum[i], arabNum[i]);
        }
        return cNum;
    }

    /**
     * 中文数值转换成阿拉伯数值
     *
     * @param cNum
     * @return
     */
    private static String cNumToANum(String cNum) {
        String[] unit = {"毛", "厘", "分", "个", "十", "百", "千", "万", "亿"};
        String[] unitValue = {"0.001", "0.01", "0.1", "1", "10", "100", "1000", "10000", "100000000"};
        String[] chinaNum = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] arabNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        //〇
        return cNumToANumByFormat(cNum, unit, unitValue, chinaNum, arabNum);
    }
    /**
     * 传入中文数值单位格式和数字格式，获取阿拉伯数值
     * @param cNum 中文数值
     * @param unitFormat 单位格式（跨度是千分到亿，既0.001~100000000）
     * @param numFormat 数值格式（0~9中文表述法）
     * @return
     */
    /**
     * 传入中文数值单位格式和数字格式，获取阿拉伯数值
     *
     * @param cNum      中文数值
     * @param unit      中问单位
     * @param unitValue 数值
     * @param chinaNum  中文数字
     * @param arabNum   数值
     * @return
     */
    private static String cNumToANumByFormat(String cNum, String[] unit, String[] unitValue, String[] chinaNum, String[] arabNum) {
        String[] unitFormulaValue = arrToArrAddFirstStr(unitValue, "*");
        String[] numValue = arrToArrAddFirstStr(arabNum, "+");
        StringBuffer unitStr = new StringBuffer();
        for (String string : unit) {
            unitStr.append(string);
        }
        for (String string : chinaNum) {
            unitStr.append(string);
        }
        cNum = cNum.replaceAll("[^" + unitStr + "]", "");
        for (int i = 0; i < unit.length; i++) {
            cNum = cNum.replaceAll(unit[i], unitFormulaValue[i]);
        }
        for (int i = 0; i < chinaNum.length; i++) {
            cNum = cNum.replaceAll(chinaNum[i], numValue[i]);
        }
        return calculateFormula(cNum.replaceFirst("^[*+]", ""));
    }

    /**
     * 把一个字符串数值加上前缀返回一个新的数组
     *
     * @param arr      数组
     * @param firstStr 前缀字符串
     * @return 数组
     */
    private static String[] arrToArrAddFirstStr(String[] arr, String firstStr) {
        String[] temp = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = firstStr + arr[i];
        }
        return temp;
    }

    /**
     * 计数字符串公式结果
     *
     * @param formula
     * @return
     */
    private static String calculateFormula(String formula) {
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            return formatDouble((Double) jse.eval(formula), "0.000");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * 格式化Double显示模式
     *
     * @param dobl
     * @param pn
     * @return
     */
    private static String formatDouble(Double dobl, String pn) {
        DecimalFormat df = new DecimalFormat(pn);
        return df.format(dobl);
    }

    /**
     * 检测字符串是否包含另一个字符串
     *
     * @param str          检测的字符串
     * @param containRegex 包含的字符串（支持正则）
     * @return 返回检查结果
     */
    public static boolean contentContain(String str, String containRegex) {
        return null == str || null == containRegex ? false :
                str.replaceAll(containRegex, "").length() < str.length();
    }

    /**
     * 处理时间，把带【上午,下午,AM,PM】的时间字符串，带下午和PM的小时加上12
     *
     * @param dateStr
     * @return
     */
    private static String treatTime(String dateStr) {
        dateStr = dateStr.trim();
        if (contentContain(dateStr, "上午|(?i)am")) {
            String[] dateStrArray = dateStr.split(" *上午 *| *(?i)am *| +");
            return formatYMD(dateStrArray[0]) + " " + formatTime(dateStrArray[1], 0);
        } else if (contentContain(dateStr, "下午|(?i)pm")) {
            String[] dateStrArray = dateStr.split(" *下午 *| *(?i)pm *| +");
            return formatYMD(dateStrArray[0]) + " " + formatTime(dateStrArray[1], 12);
        } else {
            String[] dateStrArray = dateStr.split(" +");
            return formatYMD(dateStrArray[0]) + (dateStrArray.length > 1 ? " " + formatTime(dateStrArray[1], 0) : "");
        }
    }

    /**
     * 时间字符串的小时加上小时量生成新的时间字符串
     *
     * @param timeStr
     * @param hh
     * @return
     */
    private static String formatTime(String timeStr, Integer hh) {
        String[] timeArr = timeStr.split("\\D+");
        int HH = Integer.parseInt(timeArr.length > 0 ? timeArr[0] : "0") + hh;
        String outTime = Integer.toString(HH);
        outTime = outTime.length() < 2 ? "0" + outTime : outTime;
        for (int i = 1; i < timeArr.length; i++) {
            outTime += ":" + (timeArr[i].length() < 2 ? "0" + timeArr[i] : timeArr[i]);
        }
        return outTime;
    }

    /**
     * 获得当前日期指定时间点的date对象
     * 例如：想要获得今天9:00 的日期对象 dateSpliceTime("9:00")
     *
     * @param timeStr 时分秒字符串
     * @return
     */
    public static Date getDateByTimeStr(Date date, String timeStr) {
        if (isEmpty(date) || isEmpty(timeStr)) {
            return null;
        }
        String dateStr = dateToStr(date, PNYYYYMMDD) + " " + timeStr.trim();
        return strToDate(dateStr);
    }

    /**
     * 传入指定日期后的几天的List<Date>
     *
     * @param date
     * @param num
     * @return
     */
    public static List<Date> dayOfWeeks(Date date, int num) {
        List<Date> list = new ArrayList<Date>();
        for (int i = 0; i < num; i++) {
            list.add(dateGapDays(date, i));
        }
        return list;
    }

    /**
     * 传入指定日期后的几天的List<String>
     *
     * @param date
     * @param num
     * @return
     */
    public static List<String> dayOfWeekStr(Date date, int num) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < num; i++) {

            list.add(dateToStr(dateGapDays(date, i), "MM-dd"));
        }
        return list;
    }

    /**
     * 判断传入的日期是否为同一天
     *
     * @param day1
     * @param day2
     * @return
     */
    public static boolean isSameDay(Date day1, Date day2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得8点到19点的Row索引
     * //     * @param day1
     *
     * @return
     */
    public static int getRowId(Date date) {
        double outTemp = DateUtil.betweenDateMin(getDateByTimeStr(date, "00:00:00"), date) / 30;
        int out = (int) outTemp;
//        (int)(DateUtil.betweenDateHH(schedule.getStartTime(),schedule.getEndTime())/0.5)
//        String dateStr = dateToStrHH(date);
//        int result = (int)(Integer.parseInt(dateStr)/0.5);
        return out - 18;
    }

    /**
     * 日期格式转字符串格式，精确到天
     * 默认格式 2014-12-11
     *
     * @param date
     * @return
     */
    public static String dateToStrHH(Date date) {
        return dateToStr(date, HH);
    }


    /**
     * 结束时间减去开始时间除去周末的天数
     *
     * @param strStartDate
     * @param strEndDate
     * @return 天数
     */
    public static int getDutyDays(String strStartDate, String strEndDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = df.parse(strStartDate);
            endDate = df.parse(strEndDate);
        } catch (ParseException e) {
            System.out.println("非法的日期格式,无法进行转换");
            e.printStackTrace();
        }
        //开始时间和结束时间做比较如果不等，那就把开始时间加一天，加的前期是开始时间不是周六和周日，
        int result = 0;
        while (startDate.compareTo(endDate) <= 0) {
            if (startDate.getDay() != 6 && startDate.getDay() != 0)
                result++;
            startDate.setDate(startDate.getDate() + 1);
        }
        return result;
    }

    /**
     * 时间格式
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static boolean isDateFormat(String dateStr, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        boolean dateflag = true;
        // 这里要捕获一下异常信息
        try {
            Date date = format.parse(dateStr);
            return true;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    /**
     * 获取指定日期是当年的第几周
     * @param date
     * @return
     */
//	public static Integer getWeekOfYear2(Date date) {
//        Calendar calendar=Calendar.getInstance();
//		calendar.setFirstDayOfWeek(Calendar.MONDAY);
//		calendar.setTime(date);
//		return calendar.get(Calendar.WEEK_OF_YEAR);
//	}

    /************************************************智能日期转换方法(结束)********************************************************/

    public static void main(String[] args) throws Exception {
//        String strDate = "12月11日2014年 12时11分14秒" ;
//        System.out.print(strToDate(strDate)) ;
//        Date now = strToDate("2014-12-15") ;
//        System.out.println(dateToStrSS(dayOfMonday(now)));
//        System.out.println(dateToStrSS(dayOfSunday(now)));

//		List<String> list = dayOfWeekStr(new Date(),7);
//		for (String str:list) {
//			System.out.println(str);
//		}
//        String dateStr = "2022-12-31";
//        System.out.println(getWeekOfYear(strToDate(dateStr)));
//		System.out.println(getMaxWeek("2022"));
        System.out.println(mondayOfYearWeekNum("2023", 1));


//		System.out.println(mondayOfYearWeekNum("1916",getWeekOfYear(strToDate("1916-12-31"))));

    }
}
