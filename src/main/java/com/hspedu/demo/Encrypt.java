package com.hspedu.demo;

import java.util.LinkedHashMap;
import java.util.Map;

public class Encrypt {

    /**
     * 功能说明              :测试
     * 创建人                   : zhangxianchao
     * 最后修改日期    : 2011-6-24
     */
    public static void sendArgs() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("name", "刘阿勇");
        params.put("addr", "深圳");
        params.put("miyue", encrty(params));

        System.out.println(params.toString());


        // System.out.println(MD5Encode("123456"));
    }

    public static void main(String[] args) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("name", "刘阿勇");
        // params.put("addr", "100");
        params.put("addr", "8000");
        params.put("miyue", "594f9db3e5d2db14e97b342b7811e5c8");

        String reciveMiYue = params.remove("miyue");

        String encrty = encrty(params);

        System.out.println(encrty);
        System.out.println(encrty.equals(reciveMiYue));

    }

    public static final String secret = "sdfsdf@#$@#$@121#$";

    public static String encrty(Map<String, String> params) {

        String str = params.toString() + "::" + secret;

        // return MD5Encode(str);

        return  null ;
    }
}
