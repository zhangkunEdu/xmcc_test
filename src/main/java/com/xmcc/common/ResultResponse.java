package com.xmcc.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 作为 返回响应的类 （类似于上个项目中我们使用的jsondata类）
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse<T> {

    private int code;

    private String msg;

    //返回json时忽略null的属性
    @JsonInclude(JsonInclude.Include.NON_NULL)
     private T data;

    //提供code和msg的构造方法(因为有时候 失败或者成功 不需要返回data)
    public ResultResponse(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    //失败，不携带数据，不携带信息的方法
    public static ResultResponse fail(){
        return new ResultResponse<>(ResultEnums.FAIL.getCode(),ResultEnums.FAIL.getMsg());
    }

    //失败，不携带数据，携带信息的方法
    public static ResultResponse fail(String msg){
        return new ResultResponse<>(ResultEnums.FAIL.getCode(),msg);
    }

    //失败，携带数据，不携带信息的方法
    public static<T> ResultResponse fail(T t){
        return new ResultResponse<>(ResultEnums.FAIL.getCode(),ResultEnums.FAIL.getMsg(),t);
    }

    //失败，携带数据，携带信息的方法
    public static<T> ResultResponse fail(String msg,T t){
        return new ResultResponse<>(ResultEnums.FAIL.getCode(),msg,t);
    }

    //成功，携带数据
    public static<T> ResultResponse success(T t){
        return new ResultResponse<>(ResultEnums.SUCCESS.getCode(),ResultEnums.SUCCESS.getMsg(),t);
    }

    //成功，不携带数据
    public static ResultResponse success(){
        return new ResultResponse(ResultEnums.SUCCESS.getCode(),ResultEnums.SUCCESS.getMsg());
    }



}