package com.hughes.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void set(String key, Object value, long timeout, TimeUnit unit) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Boolean setIfAbsent(String key, Object value, long timeout, TimeUnit unit){
      return  redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
    }

}