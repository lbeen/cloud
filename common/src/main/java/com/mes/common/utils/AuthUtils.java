package com.mes.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class AuthUtils {
    public static final String TOKEN_KEY = "token";

    public static final Long JWT_TTL = 3600000L;

    public static final SecretKeySpec SECRET_KEY;

    static {
        String jwtKey = "longi";
        byte[] encodedKey = Base64.getEncoder().encode(jwtKey.getBytes());
        SECRET_KEY = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static String createJWT(String subject) {
        //指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //当前系统时间
        long nowMillis = System.currentTimeMillis();
        //令牌签发时间
        Date now = new Date(nowMillis);

        //令牌过期时间设置
        long expMillis = nowMillis + JWT_TTL;
        Date expDate = new Date(expMillis);

        //封装Jwt令牌信息
        JwtBuilder builder = Jwts.builder().setSubject(subject) // 主题  可以是JSON数据
                .setIssuedAt(now)                               // 签发时间
                .signWith(signatureAlgorithm, SECRET_KEY)       // 签名算法以及密匙
                .setExpiration(expDate);                        // 设置过期时间
        return builder.compact();
    }

    public static Claims parseJWT(String jwt) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
    }
}

