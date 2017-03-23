package com.skypiea.client.service;

import com.skypiea.common.result.SPResult;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-22 15:52
 */
public interface CheckService {

    /**
     * 检查用户注册信息是否正确
     *
     * @param param
     * @param type
     * @return
     */
    SPResult checkRegisterData(String param, int type);

    /**
     * 检查用户登录信息
     *
     * @param username
     * @param password
     * @return
     */
    SPResult checkLoginData(String username, String password);
}
