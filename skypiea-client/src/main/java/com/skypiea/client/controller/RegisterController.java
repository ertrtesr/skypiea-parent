package com.skypiea.client.controller;

import com.skypiea.client.service.RegisterService;
import com.skypiea.common.result.SPResult;
import com.skypiea.system.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-22 18:07
 */

@RestController
@RequestMapping("/client/user/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public SPResult register(UserInfo userInfo) {
        SPResult result = registerService.register(userInfo);
        return result;
    }
}
