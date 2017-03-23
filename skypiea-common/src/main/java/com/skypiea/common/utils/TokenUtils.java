package com.skypiea.common.utils;

import java.util.UUID;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-23 10:30
 */
public class TokenUtils {

    public static String createToken() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString().replace("-", "");
        return token;
    }
}
