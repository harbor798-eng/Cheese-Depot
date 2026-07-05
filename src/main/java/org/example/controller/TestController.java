package org.example.controller;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}