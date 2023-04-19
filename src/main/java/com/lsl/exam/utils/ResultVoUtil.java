package com.lsl.exam.utils;

import com.lsl.exam.entity.backresult.ResultVO;
import com.lsl.exam.enums.ResultEnum;

public class ResultVoUtil {

    /**
     * 成功返回：无数据情况
     * @param <T>
     * @return
     */
    public static <T>ResultVO<T> success(){
        return ResultVoUtil.success(null);
    }

    /**
     * 成功返回：有数据的情况
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> success(T data){
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 认证过期退出重新登录
     * code=1占用,要求前端跳转到登录页面
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> successLogout(){
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(1);
        resultVO.setMessage("认证失败或者过期,请重新登录");
        resultVO.setData(null);
        return resultVO;
    }

    /**
     * 返回失败情况：未知异常
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> error(){
        return ResultVoUtil.error(ResultEnum.UNKNOWN_ERROR);

    }

    /**
     * 返回失败情况,用户自定义错误
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> error(Integer code, String message){
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setData(null);
        return resultVO;

    }

    /**
     * 返回失败情况,传入失败原因枚举
     * @param resultEnum
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> error(ResultEnum resultEnum){
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMessage(resultEnum.getMessage());
        resultVO.setData(null);
        return resultVO;

    }
}
