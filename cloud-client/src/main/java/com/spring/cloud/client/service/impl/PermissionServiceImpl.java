package com.spring.cloud.client.service.impl;

import com.spring.cloud.client.entity.User;
import com.spring.cloud.client.service.PermissionService;
import com.spring.cloud.client.service.UserBizService;
import com.spring.cloudcommon.bean.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author xucongyan
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserBizService userBizService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public UserInfo validate(String username, String password) {
        UserInfo userInfo = new UserInfo();
        User user = userBizService.getUserByUsername(username);
        if (encoder.matches(password, user.getPassword())) {
            BeanUtils.copyProperties(user, userInfo);
            userInfo.setId(user.getId().toString());
        }
        return userInfo;
    }
}
