package com.mes.mvc.utils;

import com.mes.common.utils.AuthUtils;
import io.jsonwebtoken.Claims;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class MvcAuthUtils {

    public static String getUsername() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        String jwt = request.getHeader(AuthUtils.TOKEN_KEY);
        if (!StringUtils.hasText(jwt)) {
            return null;
        }
        Claims claims = AuthUtils.parseJWT(jwt);
        Object username = claims.get("username");
        return Objects.toString(username, null);
    }

    public static String getClientIP() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return request.getHeader("x-forwarded-for");
    }

    private static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}

