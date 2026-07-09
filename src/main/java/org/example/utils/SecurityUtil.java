package org.example.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtil {


    /**
     * 获取当前登录用户id
     */
    public static Integer getUserId(){


        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();



        if(authentication == null){

            return null;

        }



        return (Integer) authentication.getPrincipal();

    }


}