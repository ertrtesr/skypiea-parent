package com.skypiea.client.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-22 20:02
 */

@Configuration
public class DruidConfiguration {

    @Bean
    public DataSource dataSource(@Value("${spring.datasource.driver-class-name}") String driverClassName,
                                 @Value("${spring.datasource.url}") String url,
                                 @Value("${spring.datasource.username}") String username,
                                 @Value("${spring.datasource.password}") String password,
                                 @Value("${spring.datasource.initialSize}") int initialSize,
                                 @Value("${spring.datasource.minIdle}") int minIdle,
                                 @Value("${spring.datasource.maxActive}") int maxActive,
                                 @Value("${spring.datasource.maxWait}") long maxWait,
                                 @Value("${spring.datasource.timeBetweenEvictionRunsMillis}") long timeBetweenEvictionRunsMillis,
                                 @Value("${spring.datasource.minEvictableIdleTimeMillis}") long minEvictableIdleTimeMillis,
                                 @Value("${spring.datasource.validationQuery}") String validationQuery,
                                 @Value("${spring.datasource.testWhileIdle}") boolean testWhileIdle,
                                 @Value("${spring.datasource.testOnBorrow}") boolean testOnBorrow,
                                 @Value("${spring.datasource.testOnReturn}") boolean testOnReturn,
                                 @Value("${spring.datasource.poolPreparedStatements}") boolean poolPreparedStatements,
                                 @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}") int maxPoolPreparedStatementPerConnectionSize,
                                 @Value("${spring.datasource.filters}") String filters) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            throw new RuntimeException("load datasource error, dbProperties is :", e);
        }
        return dataSource;
    }
}
