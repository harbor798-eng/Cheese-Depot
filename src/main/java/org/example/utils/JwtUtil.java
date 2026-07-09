package org.example.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;


public class JwtUtil {


    // 密钥
    private static final String SECRET =
            "abcdefghijklmnopqrstuvxyz1234567890";


    // token有效时间
    private static final long EXPIRE_TIME =
            1000 * 60 * 60;


    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());



    /**
     * 生成token
     */
    public static String createToken(Long userId){


        return Jwts.builder()

                //存放用户信息
                .claim("userId", userId)

                //创建时间
                .setIssuedAt(new Date())

                //过期时间
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRE_TIME
                        )
                )

                //签名
                .signWith(KEY)

                //生成字符串
                .compact();

    }




    /**
     * 解析token
     */
    public static Long getUserId(String token){


        Claims claims =
                Jwts.parserBuilder()

                        .setSigningKey(KEY)

                        .build()

                        .parseClaimsJws(token)

                        .getBody();


        return claims.get("userId", Long.class);

    }


}