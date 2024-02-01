package com.springpractice.blogapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    Dotenv dotenv = Dotenv.configure().load();
    String algoSecret = dotenv.get("ALGORITHM_SECRET");

    private Algorithm algorithm = Algorithm.HMAC256(algoSecret);

    public String createJWT(Integer id){
        String token = JWT.create()
                .withSubject(id.toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .sign(algorithm);
        return token;
    }

    public Integer getUserIDFromJWT(String token){
        String id = JWT.require(algorithm).build().verify(token).getSubject();
        return Integer.parseInt(id);
    }

    public String getTokenFromHeader(String authHeader){
        return authHeader.split(" ")[1];
    }
}
