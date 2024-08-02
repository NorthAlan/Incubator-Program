package com.xauat.exception;

import com.xauat.Pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//指定该方法捕获所有异常
    public Result exception(Exception exception) {
        exception.printStackTrace();//打印异常信息
        return Result.error("操作有误");//
    }
}