package com.spring.cloud.client.service.impl;

import com.spring.cloud.client.dao.UserDao;
import com.spring.cloud.client.entity.User;
import com.spring.cloud.client.service.UserBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @author xucongyan
 */
@Service
@CacheConfig(cacheNames = "users")
public class UserBizServiceImpl implements UserBizService {

    private static Logger logger = LoggerFactory.getLogger(UserBizServiceImpl.class);

    @Value("${spring.profiles.xcy}")
    private String hello;

    @Autowired
    private UserDao userDao;


    @Override
    @Cacheable(key = "#p0")
    public User getUserByUsername(String username) {
        logger.info("从config中心获取到的信息为:" + hello);

        User user = userDao.getUserByUsername(username);

        logger.info("将信息从数据库中读取");

        return user;
    }
}
