package com.mes.mvc.feign;

import com.google.common.net.HttpHeaders;
import com.mes.common.utils.AuthUtils;
import com.mes.mvc.utils.MvcAuthUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(AuthUtils.TOKEN_KEY, MvcAuthUtils.getToken());
        requestTemplate.header(HttpHeaders.X_FORWARDED_FOR, MvcAuthUtils.getClientIP());
    }
}
