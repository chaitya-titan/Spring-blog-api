package com.springpractice.blogapi.security.jwt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JWTServiceTest {

    private JWTService jwtService = new JWTService();

    @Test
    void canCreateJWTFromUserID(){
        Integer userId = 123123;
        String jwt = jwtService.createJWT(userId);
        System.out.println(jwt);
        assertEquals(userId, jwtService.getUserIDFromJWT(jwt));
    }

    @Test
    void getUserIDFromJWT(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjMxMjMiLCJpYXQiOjE3MDY3MTY4NTAsImV4cCI6MTcwNjcxNzc1MH0.ryoC8Lz1uMhHff2SDliH6E1XjmWabcpzT2JmaA_N8q4";
        assertEquals(123123, jwtService.getUserIDFromJWT(jwt));
    }
}
