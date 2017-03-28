package com.skypiea.client.controller;

import com.skypiea.client.service.LoginService;
import com.skypiea.common.result.SPResult;
import com.skypiea.common.utils.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-23 09:49
 */

@RestController
@RequestMapping("/client/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public SPResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            SPResult result = loginService.login(username, password);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return SPResult.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @PostMapping("/loginByShiro")
    public SPResult loginByShiro(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            SPResult result = loginService.loginByShiro(username, password);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return SPResult.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @GetMapping("/token/{token}")
    public Object getUserByToken(@PathVariable String token, String callback) {
        try {
            SPResult result = loginService.getUserByToken(token);
            //支持jsonp调用
            if (StringUtils.isNotEmpty(callback)) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //将错误消息返回给客户端
            return SPResult.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @GetMapping("/logout/{token}")
    public SPResult logout(@PathVariable String token) {
        SPResult result = loginService.logout(token);
        return result;
    }

    @GetMapping("/logoutByShiro/{token}")
    public SPResult logoutByShiro(@PathVariable String token) {
        SPResult result = loginService.logoutByShiro(token);
        return result;
    }
}
