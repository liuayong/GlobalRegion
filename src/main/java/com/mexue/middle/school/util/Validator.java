package com.mexue.middle.school.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /**
     * 判断内容不为空
     *
     * @param str
     * @return
     */
    public static boolean notEmpty(Object str) {
        if (str != null && str.toString().trim().length() > 0 && !"null".equalsIgnoreCase(str.toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断内容不为空（包含数组）
     *
     * @param str
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean notEmptyIncludeArray(Object str) {
        if (str != null && String.valueOf(str).trim().length() > 0) {
            if (str instanceof Object[]) {// 增加了数组长度判断
                Object[] array = (Object[]) str;
                if (array.length > 0)
                    return true;

                return false;
            } else if (str instanceof List<?>) {// 增加了数组长度判断
                List<Object> list = (List<Object>) str;
                if (list.size() > 0)
                    return true;

                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断内容是空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        if (str == null || str.toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断内容是空或者0
     *
     * @param obj
     * @return
     */
    public static boolean isEmptyZero(Object obj) {
        return EntityUtil.isEmpty(obj) || EntityUtil.equal(obj, "0");
    }

    /**
     * 检查对象是否为空
     *
     * @param obj 要检查的数据(数据类型: String、Number、Boolean、Collection、Map、Object[])
     * @return true: 为空; false: 不为空 <li>String：值为 null、""、length=0 时返回 true <li>
     * Number：值为 null 时返回 true <li>Boolean：值为 null、false 时返回 true <li>
     * Collection：值为 null、size=0 时返回 true <li>Map：值为 null、size=0 时返回
     * true <li>Object[]：值为 null、length=0 时返回 true
     */
    @SuppressWarnings("unchecked")
    public static boolean empty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && ((obj.toString().trim().length() == 0) || (obj.equals("null")))) {
            return true;
        } else if (obj instanceof Boolean && !((Boolean) obj)) {
            return true;
        } else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是合法邮箱地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern p = Pattern
                .compile("^\\w+([\\-+.]\\w+)*@\\w+([-.]\\w+)*\\.[a-z]{2,3}");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 只包含英文字母和数字、下划线
     *
     * @param str
     * @return
     */
    public static boolean onlyNumAndChar(String str) {
        String regex = "^[a-zA-Z0-9_]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    /**
     * 必须包含字母
     *
     * @param str
     * @return
     */
    public static boolean hasLetterAndNum(String str) {
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z].*).{6,}$");
        return pattern.matcher(str).matches();
    }

    /**
     * 是否长度符合
     *
     * @param str
     * @param min 最小
     * @param max 最大
     * @return
     */
    public static boolean lengthBetween(String str, int min, int max) {
        return str.length() >= min && str.length() <= max;
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否是整型
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        if (str == null || str.length() == 0) return false;

        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     **/
    public static boolean isMobilePhone(String str) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147)|(10[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断对象的属性是否为空
     *
     * @param entity
     * @param fields
     * @return
     */
    public static boolean empty(Object entity, String... fields) {
        if (entity == null) {
            return true;
        }

        List<String> fieldList = (fields != null ? Arrays.asList(fields) : null);
        if (fieldList == null || fieldList.isEmpty()) {
            return empty(entity);
        }

        for (String field : fieldList) {
            Object propVal = EntityUtil.getPropVal(entity, field);

            if (empty(propVal)) {
                System.err.println("参数: " + field + " 为空！！！！");
                return true;
            }
        }

        return false;
    }

    /**
     * 判断是否是一个链接
     *
     * @param str
     * @return
     */
    public static boolean isURL(String str) {

        // if(Validator.empty(str)) {
        //     return false;
        // }

        //转换为小写
        str = str.toLowerCase();

        String regex = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,4})?" // 端口- :80
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";


        return Pattern.matches(regex, str);
    }


    /**
     * 两个List 是否含有相同的内容，不考虑顺序
     *
     * @param list1
     * @param list2
     * @return
     */
    public static <E> boolean isEquals(List<E> list1, List<E> list2) {

        if (list1 == null && list2 == null)
            return true;

        if (list1 == null || list2 == null)
            return false;

        if (list1 == list2)
            return true;


        if (list1.size() != list2.size())
            return false;

        return list1.containsAll(list2) && list2.containsAll(list1);
    }


    /**
     * 判断时间参数格式是否符合"yyyy-MM-dd HH:mm:ss"
     * @param time
     * @return
     */
	/*public static boolean isDateFormatOk(String time){
		char[] chars = time.toCharArray();
		String format = "yyyy-MM-dd HH:mm:ss";
		char[] charsFormat = format.toCharArray();
		if(chars.length!=charsFormat.length){
			return false;
		}else{
			return DateUtil.isFormatDate(time,format);
		}
	}

	public static void main(String[] args){
		//System.out.println(isDateFormatOk("2017-04-12 14:48:04"));
	}*/

}
