package com.skypiea.system.controller;

import com.skypiea.common.result.SPResult;
import com.skypiea.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-29 23:05
 */

@RestController
@RequestMapping("/sys")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public SPResult login(String username, String password, HttpSession session) {
        SPResult result = loginService.login(username, password, session);
        return result;
    }

    @GetMapping("/token/{token}")
    public SPResult getUserByToken(@PathVariable String token, HttpSession session) {
        SPResult result = loginService.getUserByToken(token, session);
        return result;
    }
}
