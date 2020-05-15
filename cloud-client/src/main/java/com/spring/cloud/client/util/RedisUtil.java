package com.spring.cloud.client.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Value("${is_redis_open}")
    private boolean is_redis_open;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 从Redis中获取信息
     *
     * @param key Redis的key
     * @return 获取到的值
     */
    public String getValueByKey(String key) {
        if (is_redis_open) {
            return stringRedisTemplate.opsForValue().get(key);
        }
        return null;
    }

    /**
     * 设置永不过期的Redis的key和value
     *
     * @param key   Redis的key
     * @param value Redis的value
     */
    public void setValue(String key, String value) {
        if (is_redis_open) {
            stringRedisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 使用默认的超时时间设置Redis的key和value
     *
     * @param key   Redis的key
     * @param value Redis的value
     */
    public void setValueByDefaultTime(String key, String value) {
        setValue(key, value, 60, TimeUnit.SECONDS);
    }

    /**
     * 使用自定义的超时时间设置Redis的key和value
     *
     * @param key     Redis的key
     * @param value   Redis的value
     * @param timeout 超时时间
     * @param unit    时间单位
     */
    public void setValue(String key, String value, long timeout, TimeUnit unit) {
        if (is_redis_open) {
            stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
        }
    }
}
