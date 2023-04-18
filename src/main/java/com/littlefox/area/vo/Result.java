package com.littlefox.area.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.Date;

@ApiModel(description = "rest请求的返回模型，所有rest正常都返回该类的对象")
@Getter
public class Result<T> {

    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MESG = "处理成功";

    @ApiModelProperty(value = "处理结果code", required = true)
    private String code;
    @ApiModelProperty(value = "处理结果描述信息")
    private String mesg;
    @ApiModelProperty(value = "请求结果生成时间戳")
    private Date time;
    @ApiModelProperty(value = "处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result() {
        this.time = new Date();
    }


    /**
     * @param data 数据信息
     */
    public Result(T data) {
        this();
        this.data = data;
    }


    /**
     * @param code 编码
     * @param data 数据
     */
    public Result(String code, T data) {
        this.code = code;
        this.data = data;
        this.time = new Date();
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code 处理结果code
     * @param mesg 处理结果描述信息
     * @param data 处理结果数据信息
     */
    private Result(String code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
        this.time = new Date();
    }


    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data 处理结果数据信息
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param msg 处理结果描述信息
     * @return Result
     */
    public static <T> Result<T> success(String msg) {
        return new Result<>(SUCCESSFUL_CODE, msg, null);
    }

    /**
     * 自定义成功返回信息
     *
     * @param msg  处理结果描述信息
     * @param data 处理结果数据信息
     * @return Result
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(SUCCESSFUL_CODE, msg, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static <T> Result<T> success() {
        return success(SUCCESSFUL_MESG, null);
    }


    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean hasSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean hasFail() {
        return !hasSuccess();
    }


}
