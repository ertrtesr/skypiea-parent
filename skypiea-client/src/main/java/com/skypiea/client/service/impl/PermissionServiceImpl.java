package com.skypiea.client.service.impl;

import com.skypiea.client.service.PermissionService;
import com.skypiea.common.result.SPResult;
import com.skypiea.system.mapper.UserMapper;
import com.skypiea.system.model.PermissionInfo;
import com.skypiea.system.model.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-27 11:32
 */

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SPResult queryRoles(String username) {
        List<RoleInfo> roles = userMapper.findRolesByUsername(username);
        return SPResult.ok(roles);
    }

    @Override
    public SPResult queryPermissions(String username) {
        List<PermissionInfo> permissions = userMapper.findPermissionsByUsername(username);
        return SPResult.ok(permissions);
    }
}
