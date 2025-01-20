package com.Shoots.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveAddressData(int businessIdx, String address) {
        String key = "business:" + businessIdx + ":address";
        redisTemplate.opsForValue().set(key, address);
    }

    public String getAddressData(int businessIdx) {
        String key = "business:" + businessIdx + ":address";
        return (String) redisTemplate.opsForValue().get(key);
    }
}
