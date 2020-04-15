package com.spring.cloud.server.fallback;

import com.spring.cloud.server.feign.UserService;
import com.spring.cloud.server.service.impl.AuthServiceImpl;
import com.spring.cloudcommon.bean.JwtAuthenticationRequest;
import com.spring.cloudcommon.bean.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xucongyan
 */
@Component
public class UserServiceFallBack implements UserService {

    private static Logger logger= LoggerFactory.getLogger(UserServiceFallBack.class);

    @Override
    public UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        logger.error("服务调用超时，进入默认熔断");
        return new UserInfo();
    }
}
