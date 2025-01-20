package com.Shoots.redis;

import com.Shoots.mybatis.mapper.BusinessUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RedisService {

    @Autowired
    private BusinessUserMapper businessUserMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveAddressData(int businessIdx, String address) {
        String key = "business:" + businessIdx + ":address";
        redisTemplate.opsForValue().set(key, address, 1, TimeUnit.DAYS);
    }

    public Map<Integer, String> getAddressData(List<Integer> businessIdxList) {
        Map<Integer, String> addressMap = new HashMap<>();
        List<String> keys = businessIdxList.stream()
                .map(idx -> "business:" + idx + ":address")
                .collect(Collectors.toList());

        // Redis Pipeline 사용
        List<Object> results = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            keys.forEach(key -> connection.keyCommands().exists(key.getBytes()));
            return null;
        });

        for (int i = 0; i < keys.size(); i++) {
            if (Boolean.TRUE.equals(results.get(i))) {
                String address = (String) redisTemplate.opsForValue().get(keys.get(i));
                if (address == null) {
                    address = businessUserMapper.getAddressByBusinessIdx(businessIdxList.get(i));

                    if (address != null) {
                        redisTemplate.opsForValue().set(keys.get(i), address, 1, TimeUnit.DAYS);
                    }
                }
                addressMap.put(businessIdxList.get(i), address);
            }
        }
        return addressMap;
    }
}
