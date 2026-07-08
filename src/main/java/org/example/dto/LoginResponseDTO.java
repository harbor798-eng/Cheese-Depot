//JWT需要的DTO

package org.example.dto;


public class LoginResponseDTO {


    private UserDTO user;


    private String token;



    public LoginResponseDTO(
            UserDTO user,
            String token
    ){

        this.user = user;
        this.token = token;

    }



    public UserDTO getUser(){

        return user;

    }



    public String getToken(){

        return token;

    }

}
