package com.spring.cloud.client.service;


import com.spring.cloudcommon.bean.UserInfo;

public interface PermissionService {

    UserInfo validate(String username, String password);

}
