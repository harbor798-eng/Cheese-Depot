package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> list() {
        return service.list();
    }

    @PostMapping("/users")
    public User add(@RequestBody User user) {
        return service.add(user);
    }

    @GetMapping("/users/{id}")
    public User getById(@PathVariable Integer id){
        return service.getById(id);
    }

}