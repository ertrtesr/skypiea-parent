package com.skypiea.client.controller;

import com.skypiea.client.service.PermissionService;
import com.skypiea.common.cons.UserConstants;
import com.skypiea.common.result.SPResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @Autowired
    private Subject subject;

    @GetMapping("/roles")
    public SPResult queryRoles(String username) {
        SPResult result = permissionService.queryRoles(username);
        return result;
    }

    @GetMapping("/check")
    public SPResult checkRoles() {
        //执行业务逻辑代码
        return SPResult.ok();
    }

    @ExceptionHandler(AuthorizationException.class)
    public SPResult handleAuthorizationException() {
        return SPResult.fail(UserConstants.USER_AUTHORIZED_FAILED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public SPResult handleAuthenticationException() {
        return SPResult.fail(UserConstants.USER_AUTHENTICATED_FAILED);
    }
}
