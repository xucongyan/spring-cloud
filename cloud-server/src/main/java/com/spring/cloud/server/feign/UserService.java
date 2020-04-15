package com.spring.cloud.server.feign;

import com.spring.cloud.server.fallback.UserServiceFallBack;
import com.spring.cloudcommon.bean.JwtAuthenticationRequest;
import com.spring.cloudcommon.bean.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "xcy-cloud-client", fallback = UserServiceFallBack.class) //这个注解在进行远程调用的时候会用到
public interface UserService {

    @PostMapping(value = "/api/user/validate")
    UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);
}
