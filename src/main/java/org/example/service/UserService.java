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

    public User getById(Integer id){
        System.out.println("查询id = " + id);

        User user = userRepository.findById(id).orElse(null);

        System.out.println("查询结果 = " + user);

        return user;
    }

    public User update(Integer id, User user){

        User oldUser = userRepository.findById(id)
                .orElse(null);

        if(oldUser == null){
            return null;
        }

        oldUser.setName(user.getName());
        oldUser.setAge(user.getAge());

        return userRepository.save(oldUser);
    }

    public boolean delete(Integer id){


        if(userRepository.existsById(id)){


            userRepository.deleteById(id);

            return true;

        }


        return false;

    }
}