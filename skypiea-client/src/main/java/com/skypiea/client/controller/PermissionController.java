package com.skypiea.client.controller;

import com.skypiea.client.service.PermissionService;
import com.skypiea.common.result.SPResult;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-27 11:28
 */

@RestController
@RequestMapping("/client/user")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/roles")
    public SPResult queryRoles(String username) {
        SPResult result = permissionService.queryRoles(username);
        return result;
    }

    @RequiresRoles("注册会员")
    @GetMapping("/check")
    public SPResult checkRoles() {
        System.out.println("进来了check");
        return SPResult.ok();
    }
}
