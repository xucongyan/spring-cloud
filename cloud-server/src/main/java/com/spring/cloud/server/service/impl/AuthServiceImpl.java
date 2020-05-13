package com.spring.cloud.server.service.impl;


import com.spring.cloud.server.feign.UserService;
import com.spring.cloud.server.service.AuthService;
import com.spring.cloudcommon.bean.JWTInfo;
import com.spring.cloudcommon.bean.JwtAuthenticationRequest;
import com.spring.cloudcommon.bean.UserInfo;
import com.spring.cloudcommon.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {

    private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Resource
    private UserService userService;

    @Override
    public String login(JwtAuthenticationRequest authenticationRequest) {
        UserInfo info = userService.validate(authenticationRequest);
        logger.info("服务调用结果为:" + info.getId());
        if (!StringUtils.isEmpty(info.getId())) {
            return JwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        return null;
    }

}
