package com.spring.cloud.server.controller;

import com.spring.cloud.server.service.AuthService;
import com.spring.cloudcommon.bean.JwtAuthenticationRequest;
import com.spring.cloudcommon.bean.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xucongyan
 */
@RestController
@RequestMapping(value = "/user")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ObjectRestResponse login(@RequestBody JwtAuthenticationRequest request) {
        String token = authService.login(request);

        return new ObjectRestResponse<String>().data(token).rel(true);
    }
}
