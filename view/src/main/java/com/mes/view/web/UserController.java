package com.mes.view.web;

import com.mes.view.feign.UserFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("view/user")
public class UserController {
    private final UserFeignService userFeignService;

    @RequestMapping("getUser")
    public Map<String, String> get(String id) {
        return userFeignService.get(id);
    }
}
