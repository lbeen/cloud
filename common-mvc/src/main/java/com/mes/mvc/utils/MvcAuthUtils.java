package com.mes.mvc.utils;

import com.google.common.net.HttpHeaders;
import com.mes.common.system.dto.TokenUserInfo;
import com.mes.common.utils.AuthUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class MvcAuthUtils {
    public static String getUsername() {
        TokenUserInfo userinfo = getUserInfo();
        if (userinfo == null) {
            return null;
        }
        return userinfo.getUsername();
    }
    public static TokenUserInfo getUserInfo() {
        String token = getToken();
        if (!StringUtils.hasText(token)) {
            return null;
        }
        return AuthUtils.getUserInfo(token);
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

