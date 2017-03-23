package com.skypiea.client.controller;

import com.skypiea.common.result.SPResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-23 09:49
 */

@RestController
@RequestMapping("/client/user/login")
public class LoginController {

    @PostMapping
    public SPResult login(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletRequest request, HttpServletResponse response) {



        return null;
    }
}
