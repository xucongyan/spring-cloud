package com.spring.cloud.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.spring.cloud.client.dao.UserDao;
import com.spring.cloud.client.entity.User;
import com.spring.cloud.client.service.UserBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author xucongyan
 */
@Service
public class UserBizServiceImpl implements UserBizService {

    private static Logger logger = LoggerFactory.getLogger(UserBizServiceImpl.class);

    private final static String REDIS_KEY_PREFIX = "cloud_client_user";

    @Value("${spring.profiles.xcy}")
    private String hello;

    @Autowired
    private UserDao userDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User getUserByUsername(String username) {
        logger.info("从config中心获取到的信息为:" + hello);

        String key = REDIS_KEY_PREFIX + username;

        User user;

        String jsonString = redisTemplate.opsForValue().get(key);
        if (null != jsonString) {
            user = JSON.parseObject(jsonString, User.class);
            logger.info("从缓存中获取到了信息" + jsonString);
        } else {
            user = userDao.getUserByUsername(username);
            jsonString = JSON.toJSONString(user);
            logger.info("将信息从数据库中读取" + jsonString);

            redisTemplate.opsForValue().set(key, jsonString, 60, TimeUnit.SECONDS);
            logger.info("将信息写入了缓存中" + jsonString);
        }

        return user;
    }
}
