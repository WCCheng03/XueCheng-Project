package com.xuecheng.base.exception;

/**
 * @ClassName XueChengException
 * @Author Chen9
 * @Date 2023/11/13 16:14
 * @VERSION 1.0
 * @Description 项目自定义异常消息模型
 * @Program XueCheng-Project
 **/
public class XueChengException extends RuntimeException {

    private String errMessage;

    public XueChengException() {
        super();
    }

    public XueChengException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public static void cast(CommonError commonError) {
        throw new XueChengException(commonError.getErrMessage());
    }

    public static void cast(String errMessage) {
        throw new XueChengException(errMessage);
    }

    public String getErrMessage() {
        return errMessage;
    }

}
