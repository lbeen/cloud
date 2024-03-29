package com.mes.mvc.web.exception;

import com.mes.common.utils.Result;
import com.mes.mvc.log.utils.LogUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(Throwable.class)
    public Result<?> bindExceptionHandler(Throwable throwable) {
        if (throwable instanceof BindException) {
            String message = ((BindException) throwable).getBindingResult().getFieldErrors().stream().map(
                    FieldError::getDefaultMessage).collect(Collectors.joining("，"));
            return Result.error(message);
        }
        if (throwable instanceof ConstraintViolationException) {
            String message = ((ConstraintViolationException) throwable).getConstraintViolations().stream().map(
                    ConstraintViolation::getMessage).collect(Collectors.joining(","));
            return Result.error(message);
        }

        int logId = LogUtils.logError(throwable);
        String message = "系统错误，日志：" + logId;
        return Result.error(message);
    }
}