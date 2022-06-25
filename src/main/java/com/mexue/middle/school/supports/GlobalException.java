package com.mexue.middle.school.supports;

import lombok.Data;

@Data
public class GlobalException extends RuntimeException {

    private String msg;
    private String code;
  
    public GlobalException(String code, String msg){
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
