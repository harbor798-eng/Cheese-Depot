package org.example.dto;


import java.time.LocalDateTime;


public class UserDTO {


    private Integer id;

    private String username;

    private String email;

    private LocalDateTime createTime;

//    @Override
//    public String toString();

    public UserDTO(
            Integer id,
            String username,
            String email,
            LocalDateTime createTime
    ){

        this.id = id;
        this.username = username;
        this.email = email;
        this.createTime = createTime;

    }


    public Integer getId(){
        return id;
    }


    public String getUsername(){
        return username;
    }


    public String getEmail(){
        return email;
    }


    public LocalDateTime getCreateTime(){
        return createTime;
    }

}