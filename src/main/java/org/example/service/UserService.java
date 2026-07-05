package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> list(){
        return userRepository.findAll();
    }

    public User add(User user){
        return userRepository.save(user);
    }

}