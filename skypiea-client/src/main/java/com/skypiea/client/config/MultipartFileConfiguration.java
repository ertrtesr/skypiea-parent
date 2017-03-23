package com.skypiea.client.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 作者: huangwenjian
 * 描述: 上传文件的配置
 * 创建时间: 2017-03-22 10:06
 */
@Configuration
public class MultipartFileConfiguration {

    public static final long MAX_REQUEST_FILE_SIZE = 5 * 1024 * 1024;          //最大请求的文件大小5MB
    public static final long MAX_UPLOAD_FILE_SIZE = 10 * 1024 * 1024;          //最大上传大小10MB

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxRequestSize(MAX_REQUEST_FILE_SIZE);
        factory.setMaxFileSize(MAX_UPLOAD_FILE_SIZE);
        return factory.createMultipartConfig();
    }
}
