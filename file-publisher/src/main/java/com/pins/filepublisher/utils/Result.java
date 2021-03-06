package com.pins.filepublisher.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    private String code;
    /** 状态描述 */
    private String message;
    /** 业务数据 */
    private Object data;

    /**用户自定义返回错误CODE*/
    public static String ERROR_CODE = "-1";

    public static String SUCCESS_CODE = "200";
    private String SUCCESS_MSG = "操作成功";
    /** 构造方法 */
    public Result() {
        super();
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MSG;
    }

    public Result(Object data) {
        super();
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MSG;
        this.data = data;
    }

    /**
     * 构造方法
     * @param code		状态码
     * @param message	状态描述
     */
    public Result(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }


    public Result(String code, String message,String data) {
        super();
        this.code = code;
        this.message = message;
        this.data=data;
    }

    /**
     * 构造方法
     * @param code		状态码
     * @param message	状态描述
     * @param data		业务数据
     */
    public Result(String code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public boolean isSuccess(){
        return SUCCESS_CODE.equals(code);
    }

}


