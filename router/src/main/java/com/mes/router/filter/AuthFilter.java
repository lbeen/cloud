package com.mes.router.filter;

import com.alibaba.fastjson2.JSON;
import com.mes.common.utils.AuthUtils;
import com.mes.common.utils.Result;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getURI().getPath();
        if (path.startsWith("/system/login") || path.startsWith("/system/refreshToken")) {
            setResponseTime(response);
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst(AuthUtils.TOKEN_KEY);
        if (!StringUtils.hasText(token)) {
            return setNoAuth(response);
        }
        try {
            AuthUtils.parseToken(token);
        } catch (ExpiredJwtException e) {
            return setExpire(response);
        } catch (Throwable e) {
            e.printStackTrace();
            return setNoAuth(response);
        }

        setResponseTime(response);
        return chain.filter(exchange);
    }

    private void setResponseTime(ServerHttpResponse response) {
        response.getHeaders().add("response-time", Long.toString(new Date().getTime()));
    }

    private Mono<Void> setNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    private Mono<Void> setExpire(ServerHttpResponse response) {
        byte[] bytes = JSON.toJSONString(Result.error(HttpStatus.UNAUTHORIZED.value(), null)).getBytes(
                StandardCharsets.UTF_8);

        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer dataBuffer = response.bufferFactory().allocateBuffer().write(bytes);
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

