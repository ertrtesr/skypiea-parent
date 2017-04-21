package com.skypiea.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-02-20 18:29
 */

@SpringBootApplication
public class AppSystem  extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppSystem.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppSystem.class, args);
    }
}
