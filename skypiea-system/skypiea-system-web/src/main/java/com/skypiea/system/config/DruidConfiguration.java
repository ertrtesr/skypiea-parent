package com.skypiea.system.config;

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

        /*
        #数据源
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.url=jdbc:mysql://139.196.124.44:3306/skypiea?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
# 初始化大小，最小，最大
spring.datasource.initialSize=5
spring.datasource.minIdle=5
spring.datasource.maxActive=20
# 配置获取连接等待超时的时间
spring.datasource.maxWait=60000
# 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
spring.datasource.timeBetweenEvictionRunsMillis=60000
# 配置一个连接在池中最小生存的时间，单位是毫秒
spring.datasource.minEvictableIdleTimeMillis=300000
spring.datasource.validationQuery=SELECT 1 FROM DUAL
spring.datasource.testWhileIdle=true
spring.datasource.testOnBorrow=false
spring.datasource.testOnReturn=false
# 打开PSCache，并且指定每个连接上PSCache的大小
spring.datasource.poolPreparedStatements=true
spring.datasource.maxPoolPreparedStatementPerConnectionSize=20
         */

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
