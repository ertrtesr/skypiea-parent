package com.skypiea.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashSet;
import java.util.Set;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-23 17:54
 */
@Mapper
public class StringRedisMapper {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    RedisTemplate r;

    public void add(String key, String value) {
        template.opsForValue().set(key, value);
    }

    public void send(String key, String value) {
        template.convertAndSend(key, value);
    }

    public String getValue(String key) {
        String value = template.opsForValue().get(key);
        return value;
    }

    public Set<String> getKeys(String patternKey) {
        return template.keys(patternKey);
    }

    public Set<String> getAllValuesBy(String patternKey) {
        final Set<String> keys = getKeys(patternKey);
        final Set<String> set = new HashSet<String>(keys.size());

        for (String key : keys) {
            set.add(getValue(key));
        }
        return set;
    }

    public void delete(String key) {
        template.opsForValue().getOperations().delete(key);
    }
}
