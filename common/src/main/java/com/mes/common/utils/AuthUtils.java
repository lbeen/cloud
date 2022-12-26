package com.mes.common.utils;

import com.alibaba.fastjson2.JSON;
import com.mes.common.system.dto.TokenUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class AuthUtils {
    public static final String TOKEN_KEY = "token";
    public static final String USER_KEY = "user";

    public static final Long EXPIRE_DURATION = 30000L;

    public static final SecretKeySpec SECRET_KEY;

    static {
        String jwtKey = "longi";
        byte[] encodedKey = Base64.getEncoder().encode(jwtKey.getBytes());
        SECRET_KEY = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static String createToken(TokenUserInfo userinfo) {
        String subject = JSON.toJSONString(userinfo);
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRE_DURATION);
        JwtBuilder builder = Jwts.builder().setSubject(subject) // 主题 可以是JSON数据
                .setIssuedAt(now)                               // 签发时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 签名算法以及密匙
                .setExpiration(expireDate);                     // 设置过期时间
        return builder.compact();
    }

    public static TokenUserInfo getUserInfo(String token) {
        Claims claims;
        try {
            claims = parseToken(token);
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return JSON.parseObject(claims.getSubject(), TokenUserInfo.class);
    }

    public static Claims parseToken(String jwt) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
    }
}

