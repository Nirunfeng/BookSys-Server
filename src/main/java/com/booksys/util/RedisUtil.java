package com.booksys.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Ni runfeng
 * @description Redis工具类
 * @email 2570034697@qq.com
 * @date 2021/8/2 22:38
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    //往redis中放值
    public boolean setValue(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            logger.error("redis set value error {}", e.getMessage());
            return false;
        }
        return true;
    }

    //从redis中取值
    public Object getValue(String key) {
        if (key == null || key.isEmpty()) {
            try {
                throw new Exception("redis key not allow null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return redisTemplate.opsForValue().get(key);
    }

    //删除redis中的值
    public boolean delValue(String... keys) {
        try {
            if (keys == null || keys.length == 0) {
                throw new Exception("redis delete not allow null keys");
            }
            redisTemplate.delete(CollectionUtils.arrayToList(keys));
        } catch (Exception e) {
            logger.error("redis delete keys error {}", e.getMessage());
            return false;
        }
        return true;
    }

    //删除redis中的值
    public boolean delValue(String key) {
        try {
            if (key == null || key.isEmpty()) {
                throw new Exception("redis key not allow null");
            }
            redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("redis delete key error {}", e.getMessage());
            return false;
        }
        return true;
    }

    //修改redis中的值
    public void updateValue(String key, Object value) {
        this.delValue(key);
        this.setValue(key, value);
    }
}
