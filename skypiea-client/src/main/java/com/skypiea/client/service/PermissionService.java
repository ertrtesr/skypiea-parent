package com.skypiea.client.service;

import com.skypiea.common.result.SPResult;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-27 11:31
 */
public interface PermissionService {

    SPResult queryRoles(String username);

    SPResult queryPermissions(String username);
}
