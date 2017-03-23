package com.skypiea.client.controller;

import com.skypiea.client.service.LoginService;
import com.skypiea.common.result.SPResult;
import com.skypiea.common.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-23 09:49
 */

@RestController
@RequestMapping("/client/user/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public SPResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            SPResult result = loginService.login(username, password);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return SPResult.fail(ExceptionUtils.getStackTrace(e));
        }
    }
}
