package com.lsl.exam.entity.backresult;

import java.io.Serializable;

public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 8734349960075973936L;

    //状态码, 0为成功,其他都是失败
    private Integer code;

    //code=0时，message=成功; code !=0 时,记录失败原因
    private String message;

    //code=0时,记录返回结果
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
