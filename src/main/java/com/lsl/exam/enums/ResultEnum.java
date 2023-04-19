package com.lsl.exam.enums;

/**
 * 返回结果枚举
 */
public enum ResultEnum {
    SUCCESS(0,"成功"),
    NOT_FOUND(404,"未找到"),
    UNKNOWN_ERROR(10000,"未知异常");

    //状态码  用户自定义
    private final Integer code;

    private final String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
