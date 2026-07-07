package org.example.exception;


import org.example.entity.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseResult<String> handleBusinessException(
            BusinessException e){


        return new ResponseResult<>(
                404,
                e.getMessage(),
                null
        );

    }

}