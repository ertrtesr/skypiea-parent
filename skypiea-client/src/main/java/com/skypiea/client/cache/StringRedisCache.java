package com.skypiea.client.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-23 17:54
 */
@Component
public class StringRedisCache {

    @Autowired
    private StringRedisTemplate template;

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

    /**
     * 设置key的过期时间,以秒为单位
     *
     * @param key
     * @param timeout
     */
    public void expire(String key, long timeout) {
        template.expire(key, timeout, TimeUnit.SECONDS);
    }
}
