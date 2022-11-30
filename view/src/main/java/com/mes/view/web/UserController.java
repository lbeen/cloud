package com.mes.view.web;

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
    private final RestTemplate restTemplate;

    @RequestMapping("getUser")
    public Map<String, String> get(String id) {
        ResponseEntity<Map> entity = restTemplate.getForEntity("http://user-service/user/get?id=" + id, Map.class);
        Map<String, String> body = entity.getBody();
        return body;
    }
}
