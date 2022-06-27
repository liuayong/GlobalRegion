package com.mexue.middle.school.common;

import com.mexue.middle.school.supports.ErrorSupport;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 要输出的JSON结构体对象
 *
 * @author LeiPeng
 */
//@JsonInclude(Include.NON_NULL)
//@JsonRootName(value = "result")
@Data
public class Result<T> implements Serializable {
    
    /**
     * Default serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 默认状态买
     */
    private static final String STATU_OK_CODE = "0";
    /**
     * 本次操作状态(-1/0/其他),默认值为{code:0,msg:""} <br>
     * 0：正常<br>
     * -1：失败<br>
     * 其他：自定义值
     */
    //@JsonProperty("code")
    private String code = STATU_OK_CODE;
    
    /**
     * 消息
     */
    //@JsonProperty("msg")
    private String msg = "成功";
    
    /**
     * 数据对象
     */
    //@JsonProperty("data")
    private T data;
    
    /**
     * 系统当前时间戳
     */
    //@JsonProperty("times")
    private Long times = System.currentTimeMillis();
    
    
    public Result() {
    
    }
    
    public Result(T data) {
        this.data = data;
    }
    
    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public Result(ErrorSupport errorSupport) {
        this.code = errorSupport.getCode();
        this.msg = errorSupport.getMsg();
    }
    
    public Result(ErrorSupport errorSupport, T data) {
        this.code = errorSupport.getCode();
        this.msg = errorSupport.getMsg();
        this.data = data;
    }
    
    // /////////////////////////////////////自定义方法////////////////////////////////////////////////////
    
    //private Boolean success ;       // boolean: isSuccess/setSuccess Boolean: getSuccess/ setSuccess
    
    public boolean getSuccess() {
        return "0".equals(code);
    }
    
    /**
     * 返回错误信息，指定code和msg<br>
     *
     * @param code 状态码
     * @param msg  消息内容
     * @return Result
     */
    public static <T> Result<T> ERROR(String code, String msg) {
        return new Result<T>(code, msg);
    }
    
    public static <T> Result<T> ERROR(ErrorSupport errorSupport) {
        return ERROR(errorSupport.getCode(), errorSupport.getMsg());
    }
    
    public static <T> Result<T> ERROR(String code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }
    
    public static <T> Result<T> ERROR(ErrorSupport errorSupport, T data) {
        return ERROR(errorSupport.getCode(), errorSupport.getMsg(), data);
    }
    
    
    /**
     * 返回默认成功信息 code:0 msg:""
     *
     * @return Result
     */
    public static Result OK() {
        return new Result();
    }
    
    /**
     * 返回数据
     *
     * @param data 要返回的数据
     * @return Result
     */
    public static <T> Result<T> OK(T data) {
        return new Result(data);
    }
    
    /**
     * 返回默认成功信息 code:0 msg:""
     *
     * @param K data对象节点中的key
     * @param V value
     * @return
     */
    public static Result<Map<String, Object>> OK(String K, Object V) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(K, V);
        return new Result(resultMap);
    }
}
