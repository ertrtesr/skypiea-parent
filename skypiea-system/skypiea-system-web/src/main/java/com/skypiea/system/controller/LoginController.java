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

    @GetMapping("/checkAdmin")
    public SPResult checkAdmin(String username) {
        SPResult result = loginService.checkAdmin(username);
        return result;
    }

    @PostMapping("/login")
    public SPResult login(String username, String password, HttpSession session) {
        SPResult result = loginService.login(username, password, session);
        return result;
    }

    @GetMapping("/token/{token}")
    public SPResult getAdminByToken(@PathVariable String token, HttpSession session) {
        SPResult result = loginService.getUserByToken(token, session);
        return result;
    }

    @GetMapping("/logout/{token}")
    public SPResult logout(@PathVariable String token, HttpSession session) {
        SPResult result = loginService.logout(token, session);
        return result;
    }
}
