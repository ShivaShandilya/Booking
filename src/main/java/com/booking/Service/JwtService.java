package com.booking.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.booking.Dto.LoginDto;
import com.booking.entity.PropertyUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.algothim.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
@Value("${jwt.expiry.time}")
    private int expiryTime;
    private Algorithm algorithm;
    private final static String USER_NAME="username";

    @PostConstruct
    public void postConstuctor(){
        algorithm= Algorithm.HMAC256(algorithmKey);

    }

   public String generateToken(PropertyUser user1){
      return JWT.create().withClaim(USER_NAME,user1.getUsername()).
            withExpiresAt(new Date(System.currentTimeMillis()+expiryTime)).
             withIssuer(issuer).sign(algorithm);
    }
    public String getUserName(String token){
        DecodedJWT verify = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
     return    verify.getClaim(USER_NAME).asString();
    }

}