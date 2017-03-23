package com.skypiea.client.service;

import com.skypiea.common.result.SPResult;
import com.skypiea.system.model.UserInfo;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-22 15:36
 */
public interface RegisterService {

    SPResult register(UserInfo userInfo);
}
