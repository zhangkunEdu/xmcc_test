package com.xmcc.exception;


import com.xmcc.common.ResultEnums;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException {

    //枚举类中 成功或者失败的 code
    private int code;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        this(ResultEnums.FAIL.getCode(),message);
    }

    public CustomException(int code,String message) {
        super(message);
        this.code = code;
    }
}