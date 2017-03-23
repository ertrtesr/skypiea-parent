package com.skypiea.client.controller;

import com.skypiea.client.service.CheckService;
import com.skypiea.common.result.SPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-22 16:19
 */

@RequestMapping("/client/user/check")
@RestController
public class CheckController {

    @Autowired
    private CheckService checkService;

    @GetMapping("/{param}/{type}")
    public SPResult checkData(@PathVariable String param, @PathVariable int type) {
        SPResult result = checkService.checkRegisterData(param, type);
        return result;
    }
}
