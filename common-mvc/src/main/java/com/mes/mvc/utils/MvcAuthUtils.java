package com.mes.mvc.utils;

import com.alibaba.fastjson2.JSON;
import com.google.common.net.HttpHeaders;
import com.mes.common.utils.AuthUtils;
import io.jsonwebtoken.Claims;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class MvcAuthUtils {
    public static String getUsername() {
        String jwt = getToken();
        if (!StringUtils.hasText(jwt)) {
            return null;
        }
        Claims claims = AuthUtils.parseToken(jwt);
        String subject = claims.getSubject();
        if (!StringUtils.hasText(subject)) {
            return null;
        }
        return JSON.parseObject(subject).getString("username");
    }

    public static String getToken() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return request.getHeader(AuthUtils.TOKEN_KEY);
    }

    public static String getClientIP() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }

        return request.getHeader(HttpHeaders.X_FORWARDED_FOR);
    }

    private static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}

