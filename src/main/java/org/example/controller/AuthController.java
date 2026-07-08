package org.example.controller;

import org.example.dto.LoginDTO;
import org.example.dto.RegisterDTO;
import org.example.dto.UserDTO;

import org.example.entity.ResponseResult;
import org.example.entity.User;

import org.example.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;


    public AuthController(AuthService authService){

        this.authService = authService;

    }


//前端不返回密码
    @PostMapping("/register")
    public ResponseResult<UserDTO> register(
            @RequestBody RegisterDTO dto){

        System.out.println("进入注册接口");

        User user = authService.register(dto);

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreateTime()
        );


        return new ResponseResult<>(
                200,
                "注册成功",
                userDTO
        );

    }
    @PostMapping("/login")
    public ResponseResult<UserDTO> login(
            @RequestBody LoginDTO dto
    ){

        User user = authService.login(dto);

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreateTime()
        );

        return new ResponseResult<>(
                200,
                "登录成功",
                userDTO
        );

    }

}