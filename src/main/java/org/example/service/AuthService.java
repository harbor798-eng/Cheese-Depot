package org.example.service;


import org.example.dto.LoginDTO;
import org.example.dto.RegisterDTO;
import org.example.entity.User;
import org.example.exception.BusinessException;
import org.example.repository.SpaceRepository;
import org.example.repository.UserRepository;
import org.example.entity.Space;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.entity.SpaceMember;
import org.example.repository.SpaceMemberRepository;

import java.time.LocalDateTime;


@Service
public class AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SpaceRepository spaceRepository;
    private final SpaceMemberRepository spaceMemberRepository;


    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            SpaceRepository spaceRepository,
            SpaceMemberRepository spaceMemberRepository
    ){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.spaceRepository = spaceRepository;
        this.spaceMemberRepository = spaceMemberRepository;

    }



    public User register(RegisterDTO dto){


        //1. 判断用户名是否存在

        User existUser =
                userRepository.findByUsername(dto.getUsername());


        if(existUser != null){

            throw new BusinessException(409,"用户名已存在");

        }



        //2. DTO转换Entity

        User user = new User();


        user.setUsername(dto.getUsername());


        user.setPassword(
                passwordEncoder.encode(
                        dto.getPassword()
                )
        );


        user.setEmail(dto.getEmail());


// 初始化用户信息

        user.setStatus(1);


        user.setAccountType("NORMAL");


        user.setCreateTime(
                LocalDateTime.now()
        );


        user.setUpdateTime(
                LocalDateTime.now()
        );



        //3. 保存数据库

        User saveUser = userRepository.save(user);

        // 创建个人空间

        Space space = new Space();


        space.setName(
                saveUser.getUsername()
                        + "的个人知识库"
        );


        space.setType("PERSONAL");


        space.setOwnerId(
                saveUser.getId()
        );


        space.setDescription(
                "个人私有知识空间"
        );


        space.setCreateTime(
                LocalDateTime.now()
        );


        space.setUpdateTime(
                LocalDateTime.now()
        );


        spaceRepository.save(space);

        // 创建空间成员关系

        SpaceMember member = new SpaceMember();


        member.setSpaceId(
                space.getId()
        );


        member.setUserId(
                saveUser.getId()
        );


        member.setRole(
                "OWNER"
        );

        member.setJoinTime(
                LocalDateTime.now()
        );


        spaceMemberRepository.save(member);
  

        return saveUser;
    }

    public User login(LoginDTO dto){


        //1. 根据用户名查询用户

        User user =
                userRepository.findByUsername(
                        dto.getUsername()
                );


        if(user == null){

            throw new BusinessException(404,"用户不存在");

        }



        //2. 校验密码

        if(!passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
        )){


            throw new BusinessException(400,"密码错误");

        }



        return user;

    }

}