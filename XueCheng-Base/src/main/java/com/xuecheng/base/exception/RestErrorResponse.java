package com.xuecheng.base.exception;

/**
 * @ClassName RestErrorResponse
 * @Author Chen9
 * @Date 2023/11/13 16:13
 * @VERSION 1.0
 * @Description 和前端约定返回的异常信息
 * @Program XueCheng-Project
 **/

import lombok.Data;

import java.io.Serializable;

/**
 * 错误响应参数包装
 */
@Data
public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
