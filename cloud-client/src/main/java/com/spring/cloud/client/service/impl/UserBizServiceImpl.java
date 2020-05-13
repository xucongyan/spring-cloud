package com.spring.cloud.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.spring.cloud.client.dao.UserDao;
import com.spring.cloud.client.entity.User;
import com.spring.cloud.client.service.UserBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * @author xucongyan
 */
@Service
public class UserBizServiceImpl implements UserBizService {

    private static Logger logger = LoggerFactory.getLogger(UserBizServiceImpl.class);

    private final String REDIS_KEY_PREFIX = "CLOUD_CLIENT";

    @Autowired
    private UserDao userDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User getUserByUsername(String username) {
        String redisKey = REDIS_KEY_PREFIX + username;

        String jsonString = redisTemplate.opsForValue().get(redisKey);

        User user;

        if (jsonString != null) {
            user = JSON.parseObject(jsonString, User.class);

            logger.info("将信息从缓存中中读取");
        } else {
            user = userDao.getUserByUsername(username);
            jsonString = JSON.toJSONString(user);
            redisTemplate.opsForValue().set(redisKey, jsonString, 60, TimeUnit.SECONDS);

            logger.info("将信息从数据库中读取");
        }

        return user;
    }
}
