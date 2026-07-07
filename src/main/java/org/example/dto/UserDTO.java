package org.example.dto;


public class UserDTO {


    private String name;


    private Integer age;


    public UserDTO(String name,Integer age){

        this.name=name;
        this.age=age;

    }


    public String getName(){

        return name;

    }


    public Integer getAge(){

        return age;

    }

}