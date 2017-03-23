package com.skypiea.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 作者: huangwenjian
 * 描述: Redis配置类
 * 创建时间: 2017-03-23 20:36
 */

@Configuration
public class RedisConfiguration {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    /**
     * 对string类型缓存的支持
     *
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
