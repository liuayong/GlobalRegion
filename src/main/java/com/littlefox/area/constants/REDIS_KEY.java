package com.littlefox.area.constants;


/**
 * 定义Redis中的KEY
 */
public class REDIS_KEY {
    
    /**
     * 手机发送的短信验证码的KEY,格式:KEY_SMS_VAILD_CODE{SMS_TYPE}_{phone}
     * 示例:KEY_SMS_VAILD_CODE_E1_13001000000
     */
    public static final String KEY_SMS_VAILD_CODE = "SMS::KEY_SMS_VAILD_CODE_{0}_{1}";
    
    /**
     * 同一IP连续发送短信的次数KEY,格式:KEY_SMS_SEND_CODE_COUNT_{deviceId}
     * 示例:KEY_SMS_SEND_CODE_COUNT_JDKSFJIWEFJIWEFSLFKDF
     */
    public static final String KEY_SMS_SEND_CODE_COUNT = "SMS::KEY_SMS_SEND_CODE_COUNT_{0}";
    
    /**
     * 同一IP连续发送5次短信,则需要图片验证码核对.图片验证码值存储KEY,格式:KEY_SMS_VALID_IMG_CODE_{deviceId}
     * 示例:KEY_SMS_VALID_IMG_CODE_QWKFJIWEFJISLDFKSDFSDF
     */
    public static final String KEY_SMS_VALID_IMG_CODE = "SMS::KEY_SMS_VALID_IMG_CODE_{0}";
    
    
}
