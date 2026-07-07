package org.example.controller;

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

    @GetMapping("/users/{id}")
    public ResponseResult<User> getById(
            @PathVariable Integer id){


        User user = userService.getById(id);


        if(user == null){

            return new ResponseResult<>(
                    404,
                    "用户不存在",
                    null
            );

        }


        return new ResponseResult<>(
                200,
                "查询成功",
                user
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