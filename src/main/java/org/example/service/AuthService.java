package org.example.service;


import org.example.dto.LoginDTO;
import org.example.dto.RegisterDTO;
import org.example.entity.User;
import org.example.exception.BusinessException;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }



    public User register(RegisterDTO dto){


        //1. 判断用户名是否存在

        User existUser =
                userRepository.findByUsername(dto.getUsername());


        if(existUser != null){

            throw new BusinessException("用户名已存在");

        }



        //2. DTO转换Entity

        User user = new User();

        user.setUsername(dto.getUsername());

        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        user.setEmail(dto.getEmail());



        //3. 保存数据库

        return userRepository.save(user);

    }

    public User login(LoginDTO dto){


        //1. 根据用户名查询用户

        User user =
                userRepository.findByUsername(
                        dto.getUsername()
                );


        if(user == null){

            throw new BusinessException("用户不存在");

        }



        //2. 校验密码

        if(!passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
        )){


            throw new BusinessException("密码错误");

        }



        return user;

    }

}