package com.skypiea.client.service;

import com.skypiea.common.result.SPResult;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-23 09:49
 */
public interface LoginService {

    SPResult login(String username, String password);

    SPResult getUserByToken(String token);
}
