package org.example.controller;

import org.example.dto.UserDTO;
import org.example.dto.UpdateUserDTO;
import org.example.entity.ResponseResult;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){

        this.userService = userService;

    }

    @GetMapping("/users")
    public List<User> list() {
        return userService.list();
    }

    @PostMapping("/users")
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @GetMapping("/users/{id:\\d+}")
    public ResponseResult<UserDTO> getById(
            @PathVariable Integer id){


        UserDTO userDTO = userService.getById(id);


        return new ResponseResult<>(
                200,
                "查询成功",
                userDTO
        );

    }

    @PutMapping("/users/me")
    public ResponseResult<UserDTO> updateCurrentUser(
            @RequestBody UpdateUserDTO dto
    ){

        System.out.println("进入修改用户接口");

        UserDTO userDTO =
                userService.updateCurrentUser(dto);


        return new ResponseResult<>(
                200,
                "修改成功",
                userDTO
        );

    }

    @GetMapping("/users/me")
    public ResponseResult<UserDTO> getCurrentUser(){

        System.out.println("进入/users/me接口");

        UserDTO userDTO =
                userService.getCurrentUser();


        return new ResponseResult<>(
                200,
                "查询成功",
                userDTO
        );

    }

    @PutMapping("/users/{id}")
    public User update(
            @PathVariable Integer id,
            @RequestBody User user){

        return userService.update(id,user);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Integer id){

        userService.delete(id);

    }
}