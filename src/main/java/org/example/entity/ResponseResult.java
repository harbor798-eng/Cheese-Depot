package org.example.entity;


public class ResponseResult<T> {


    private Integer code;

    private String message;

    private T data;

    public ResponseResult(Integer code,String message,T data){

        this.code = code;
        this.message = message;
        this.data = data;

    }


    public Integer getCode(){
        return code;
    }


    public String getMessage(){
        return message;
    }


    public T getData(){
        return data;
    }


}