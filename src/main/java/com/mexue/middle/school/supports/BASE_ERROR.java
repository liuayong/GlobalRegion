package com.mexue.middle.school.supports;

public class BASE_ERROR implements ErrorSupport {


    public static final BASE_ERROR ERR_SERVER_ERR=new BASE_ERROR("90000","服务器异常!");
    public static final BASE_ERROR ERR_BASE_ERR=new BASE_ERROR("90001","未知异常!");
    public static final BASE_ERROR ERR_TOKEN_VALID=new BASE_ERROR("90004","Token验证失败!");
    public static final BASE_ERROR ERR_TOKEN_CREATE_ERROR=new BASE_ERROR("90005","Token生成失败,userId不能为空!");
    public static final BASE_ERROR ERR_REFTOKEN_CREATE_ERROR=new BASE_ERROR("90006","refToken生成失败,userId不能为空!");

    public static final BASE_ERROR ERR_TOKEN_NULL=new BASE_ERROR("90007","请传入有效的Token!");
    public static final BASE_ERROR ERR_REFTOKEN_NULL=new BASE_ERROR("90008","请传入有效的RefreToken!");

    public static final BASE_ERROR ERR_404 = new BASE_ERROR("404","404 (#^.^#) 迷路了!  {0}  {1}");
    public static final BASE_ERROR ERR_MISS_PARAM_ERROR = new BASE_ERROR("400","缺少参数:{0} {1} .");
    public static final BASE_ERROR ERR_REQUEST_METHOD_ERROR = new BASE_ERROR("405","Method {0} 不被支持,正确的应该是: {1} .");


    private String code;
    private String msg;

    BASE_ERROR(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
