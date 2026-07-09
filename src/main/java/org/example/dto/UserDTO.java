package org.example.dto;


import java.time.LocalDateTime;


public class UserDTO {


    private Long id;

    private String username;

    private String email;

    private LocalDateTime createTime;

//    @Override
//    public String toString();

    public UserDTO(
            Long id,
            String username,
            String email,
            LocalDateTime createTime
    ){

        this.id = id;
        this.username = username;
        this.email = email;
        this.createTime = createTime;

    }


    public Long getId(){
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