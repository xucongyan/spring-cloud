package com.spring.cloud.server.service;

import com.spring.cloudcommon.bean.JwtAuthenticationRequest;

public interface AuthService {
    String login(JwtAuthenticationRequest authenticationRequest);

}
