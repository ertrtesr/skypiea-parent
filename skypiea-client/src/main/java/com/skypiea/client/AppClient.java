package com.skypiea.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 作者: huangwenjian
 * 描述: 启动类,如果需要注入依赖工程中的mapper,service,需要定义mapperscan和componentscan的basepackages
 * 创建时间: 2017-02-20 18:29
 */

@SpringBootApplication
@MapperScan(basePackages = {"com.skypiea.system.mapper", "com.skypiea.client.*.mapper"})
public class AppClient {
    public static void main(String[] args) {
        SpringApplication.run(AppClient.class, args);
    }
}
