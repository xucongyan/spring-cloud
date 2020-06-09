package com.spring.cloud.client.util;

import com.spring.cloud.client.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * redis工具类
 */
@Component
public class RedisUtil {

    @Value("${is_redis_open}")
    private boolean is_redis_open;

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 尝试获取分布式锁
     *
     * @param key      key
     * @param value    value
     * @param expire   失效时间
     * @param timeUnit 时间单位
     * @return 是否锁定成功
     */
    public synchronized boolean lock(String key, String value, long expire, TimeUnit timeUnit) {
        return (boolean) redisTemplate.execute((RedisCallback<Object>) connection -> {
            return connection.set(key.getBytes(), value.getBytes(), Expiration.from(expire, timeUnit), RedisStringCommands.SetOption.SET_IF_ABSENT);
        });
    }

    /**
     * 尝试释放分布式锁
     *
     * @param key   key
     * @param value value
     * @return 是否释放成功
     */
    public synchronized boolean releaseLock(String key, String value) {
        return (boolean) redisTemplate.execute((RedisCallback<Object>) connection -> {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            return connection.eval(script.getBytes(), ReturnType.BOOLEAN, 1, key.getBytes(),
                    value.getBytes());
        });
    }

    /**
     * 指定key缓存失效时间
     *
     * @param key      key
     * @param expire   失效时间
     * @param timeUnit 时间单位
     * @return 是否设置成功
     */
    public boolean expire(String key, long expire, TimeUnit timeUnit) {
        if (expire > 0) {
            redisTemplate.expire(key, expire, timeUnit);
            return true;
        }
        return false;
    }

    /**
     * 获取key的失效时间
     *
     * @param key key
     * @return 失效时间，返回0代表永久有效
     */
    public long getExpire(String key) {
        return Optional.ofNullable(key).map(u -> redisTemplate.getExpire(key)).get();
    }

    /**
     * 判断缓存中是否含有key
     *
     * @param key key
     * @return 判断结果
     */
    public boolean hashKey(String key) {
        return Optional.ofNullable(key).map(u -> redisTemplate.hasKey(key)).orElse(false);
    }

    /**
     * 删除一个或者多个key
     *
     * @param key key
     * @return 是否删除成功
     */
    public boolean delete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                return Optional.ofNullable(key[0]).map(u -> redisTemplate.delete(key[0])).get();
            } else {
                Long result = redisTemplate.delete(new ArrayList<>(Arrays.asList(key)));
                return Objects.equals(key.length, Optional.ofNullable(result).orElse(-1L).intValue());
            }
        }
        return false;
    }


    /**
     * 从Redis中获取信息
     *
     * @param key Redis的key
     * @return 获取到的值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置永不过期的Redis的key和value
     *
     * @param key   Redis的key
     * @param value Redis的value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * 使用自定义的超时时间设置Redis的key和value
     *
     * @param key     Redis的key
     * @param value   Redis的value
     * @param timeout 超时时间
     * @param unit    时间单位
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        if (timeout > 0) {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        } else {
            this.set(key, value);
        }
    }

    /**
     * 递增
     *
     * @param key   key
     * @param delta 需要递增的大小
     * @return 执行递增之后的结果
     */
    public long incr(String key, long delta) {
        return Optional.ofNullable(key).map(u -> redisTemplate.opsForValue().increment(key, delta)).orElse(0L);
    }

    /**
     * 递减
     *
     * @param key   key
     * @param delta 需要递减的大小
     * @return 执行递减之后的结果
     */
    public long decr(String key, long delta) {
        return Optional.ofNullable(key).map(u -> redisTemplate.opsForValue().increment(key, -delta)).orElse(0L);
    }

    /**
     * 获取hash中的值
     *
     * @param key     key
     * @param hashKey hashKey
     * @return 获取到的值
     */
    public Object hGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取RedisKey所有的hash键值对
     *
     * @param key Redis的key
     * @return 获取到的值
     */
    public Map<Object, Object> hashGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
}
