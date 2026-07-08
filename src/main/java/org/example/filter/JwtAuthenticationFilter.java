package org.example.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.utils.JwtUtil;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


@Component
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {



        //1. 获取请求头中的token

        String header =
                request.getHeader("Authorization");



        //2. 判断有没有token

        if(header != null
                && header.startsWith("Bearer ")){


            // 去掉Bearer 前缀

            String token =
                    header.substring(7);



            //3. 解析token

            Integer userId =
                    JwtUtil.getUserId(token);



            System.out.println(
                    "当前登录用户id = "
                            + userId
            );

            //创建认证对象
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            null
                    );


            //放入 SecurityContext
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);


        }



        //4. 放行请求

        filterChain.doFilter(
                request,
                response
        );

    }

}