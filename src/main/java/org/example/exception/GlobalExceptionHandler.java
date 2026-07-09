package org.example.exception;


import org.example.entity.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler {



    //业务异常
    @ExceptionHandler(BusinessException.class)
    public ResponseResult<Void> handleBusinessException(
            BusinessException e
    ){


        return new ResponseResult<>(
                e.getCode(),
                e.getMessage(),
                null
        );

    }

    //参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Void> handleValidationException(
            MethodArgumentNotValidException e
    ){


        FieldError error =
                e.getBindingResult()
                        .getFieldError();


        String message =
                error != null
                        ? error.getDefaultMessage()
                        : "参数错误";


        return new ResponseResult<>(
                400,
                message,
                null
        );

    }


}