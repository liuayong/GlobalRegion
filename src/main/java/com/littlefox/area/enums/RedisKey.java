package com.littlefox.area.enums;


import com.littlefox.area.utils.Client;

import java.text.MessageFormat;

public class RedisKey {

    /**
     * 登陆Redis KEY 格式: token::用户类型::用户ID字符串
     * 例如:token::student::100 学生ID100的用户登录票据
     */
    public static final String KEY_TOKEN = "token::{0}::{1}";
    /**
     * 字段数据
     */
    public static final String KEY_DICT = "dict:code:{0}";
    /**
     * 地区
     */
    public static final String KEY_AREA = "area:code:{0}";

    /**
     * 机构
     */
    public static final String KEY_ORG = "org::all";



    public static String formatTokenKey(Client client, RoleType roleType,String userId,String token){
        String key = MessageFormat.format(RedisKey.KEY_TOKEN,roleType,userId);
        if(client == Client.PC){
            key = key + "::"+token;
        }
        return key;
    }
    
    
}
