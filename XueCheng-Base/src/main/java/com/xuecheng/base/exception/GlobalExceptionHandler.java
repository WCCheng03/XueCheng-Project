package com.xuecheng.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GlobalExceptionHandler
 * @Author Chen9
 * @Date 2023/11/13 16:19
 * @VERSION 1.0
 * @Description 对自定义异常的处理
 * @Program XueCheng-Project
 **/
@ControllerAdvice//控制器增强
@Slf4j
//@RestControllerAdvice
public class GlobalExceptionHandler {
    //处理XueChengException异常，此类是程序员主动抛出的，可预知异常
    @ResponseBody//将信息返回为json格式
    @ExceptionHandler(XueChengException.class)//此方法捕获XueChengException异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//状态码返回500
    public RestErrorResponse customException(XueChengException e) {
        log.error("【系统异常】{}",e.getErrMessage(),e);
        return new RestErrorResponse(e.getErrMessage());

    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse exception(Exception e) {

        log.error("【系统异常】{}",e.getMessage(),e);

        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
    }

    // 获取JSR303框架的错误信息
    // 解析MethodArgumentNotValidException
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        //存错误信息
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(item->{
            errors.add(item.getDefaultMessage());
        });
        //将errors中的错误信息拼接起来
        String errMessage = StringUtils.join(errors, ",");
        log.error("【系统异常】{}",e.getMessage(),errMessage);

        return new RestErrorResponse(errMessage);
    }


}
