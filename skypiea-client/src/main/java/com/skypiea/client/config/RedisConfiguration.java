package com.skypiea.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 作者: huangwenjian
 * 描述: Redis配置类
 * 创建时间: 2017-03-23 20:36
 */

@Configuration
@ComponentScan(basePackages = {"com.skypiea.client.cache"})
public class RedisConfiguration {

    /**
     * #Redis
     * spring.redis.database=0
     * spring.redis.host=139.196.124.44
     * spring.redis.pool.max-active=8
     * spring.redis.pool.max-idle=8
     * spring.redis.pool.max-wait=-1
     * spring.redis.port=6379
     * spring.redis.timeout=0
     */

    JedisConnectionFactory factory;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWait;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setDatabase(database);
        factory.setTimeout(timeout);
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        factory.setPoolConfig(poolConfig);
        return factory;
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
