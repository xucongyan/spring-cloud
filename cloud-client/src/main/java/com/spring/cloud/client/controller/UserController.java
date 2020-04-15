package com.spring.cloud.client.controller;


import com.spring.cloudcommon.bean.JwtAuthenticationRequest;
import com.spring.cloud.client.service.PermissionService;
import com.spring.cloudcommon.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xucongyan
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping(value = "/user/validate")
    public UserInfo validate(@RequestBody JwtAuthenticationRequest request) {
        return permissionService.validate(request.getUsername(),request.getPassword());
    }

}
