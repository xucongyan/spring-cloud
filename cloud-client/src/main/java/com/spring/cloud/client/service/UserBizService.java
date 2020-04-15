package com.spring.cloud.client.service;

import com.spring.cloud.client.entity.User;

public interface UserBizService {

    User getUserByUsername(String username);
}
