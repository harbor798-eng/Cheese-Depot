package org.example.service;

import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.dto.UpdatePasswordDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.example.utils.SecurityUtil;
import org.example.dto.UpdateUserDTO;
import org.example.exception.BusinessException;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> list(){
        return userRepository.findAll();
    }

    public User add(User user){
        return userRepository.save(user);
    }

    public UserDTO getById(Integer id){


        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new BusinessException("用户不存在")
                );


        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreateTime()
        );

    }

    public User update(Integer id, User user){

        User oldUser = userRepository.findById(id)
                .orElse(null);

        if(oldUser == null){
            return null;
        }

        oldUser.setId(user.getId());
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setCreateTime(user.getCreateTime());

        return userRepository.save(oldUser);
    }

    public boolean delete(Integer id){


        if(userRepository.existsById(id)){


            userRepository.deleteById(id);

            return true;

        }


        return false;

    }

    /**
     * 获取当前登录用户信息
     */
    public UserDTO getCurrentUser(){


        // 获取当前登录用户id
        Integer userId = SecurityUtil.getUserId();


        if(userId == null){

            throw new BusinessException("用户未登录");

        }



        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new BusinessException("用户不存在")
                );



        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreateTime()
        );

    }

    /**
     * 修改当前登录用户信息
     */
    public UserDTO updateCurrentUser(UpdateUserDTO dto){


        // 获取当前登录用户id
        Integer userId = SecurityUtil.getUserId();


        if(userId == null){

            throw new BusinessException("用户未登录");

        }



        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new BusinessException("用户不存在")
                );



        // 修改允许修改的字段

        user.setUsername(dto.getUsername());

        user.setEmail(dto.getEmail());



        User saveUser =
                userRepository.save(user);



        return new UserDTO(
                saveUser.getId(),
                saveUser.getUsername(),
                saveUser.getEmail(),
                saveUser.getCreateTime()
        );

    }

    public void updatePassword(UpdatePasswordDTO dto){


        // 获取当前用户id
        Integer userId = SecurityUtil.getUserId();


        if(userId == null){

            throw new BusinessException("用户未登录");

        }



        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new BusinessException("用户不存在")
                );



        // 校验旧密码

        boolean matches =
                passwordEncoder.matches(
                        dto.getOldPassword(),
                        user.getPassword()
                );


        if(!matches){

            throw new BusinessException("旧密码错误");

        }



        // 加密新密码

        user.setPassword(
                passwordEncoder.encode(
                        dto.getNewPassword()
                )
        );


        userRepository.save(user);

    }

}