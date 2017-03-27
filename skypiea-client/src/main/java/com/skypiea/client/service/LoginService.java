package com.skypiea.client.service;

import com.skypiea.common.result.SPResult;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-23 09:49
 */
public interface LoginService {

    SPResult login(String username, String password);

    SPResult loginByShiro(String username, String password);

    //根据token取用户信息
    SPResult getUserByToken(String token);

    //安全退出
    SPResult logout(String token);

    SPResult logoutByShiro(String token);
}
