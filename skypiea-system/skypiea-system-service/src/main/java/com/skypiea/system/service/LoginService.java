package com.skypiea.system.service;

import com.skypiea.common.result.SPResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-29 19:10
 */
public interface LoginService {

    SPResult login(String username, String password, HttpServletRequest request);
}
