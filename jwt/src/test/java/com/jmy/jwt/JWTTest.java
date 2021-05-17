package com.jmy.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JWTTest {

    public static final String signature = "token!jmy@bj";
    // 生成Token
    @Test
    public void testCreateToken(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,9);
        // 生成jwt
        JWTCreator.Builder builder = JWT.create();

        String token = builder.withClaim("name","jiangmingyang")
                .withExpiresAt(calendar.getTime()) // 设置过期时间
                    .sign(Algorithm.HMAC256(signature)); // 设置JWT的签名Signature部分

        System.out.println(token);
    }

    // 验证Token 解析payload数据
    @Test
    public void testVerifierToken(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiamlhbmdtaW5neWFuZyIsImV4cCI6MTYwMDQ0MzY3M30.dL7T3JVTlNj40F0d5MoE_9DXo68tZgXpDf7KttXeL3o";
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(signature)).build();
        DecodedJWT jwt = verifier.verify(token);
        System.out.println("用户名：" + jwt.getClaim("name").asString());
        System.out.println("过期时间：" + jwt.getExpiresAt());

    }
}
