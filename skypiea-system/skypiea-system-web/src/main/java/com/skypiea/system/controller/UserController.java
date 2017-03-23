package com.skypiea.system.controller;

import com.skypiea.common.result.SPResult;
import com.skypiea.system.model.UserInfo;
import com.skypiea.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作者: huangwenjian
 * 描述: 用户管理
 * 创建时间: 2017-02-20 19:02
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    private SPResult findUserById(@PathVariable int id) {
        SPResult result = userService.findUserById(id);
        return result;
    }

    /**
     * RequestParam注解相当于给username取了一个别名
     *
     * @param username
     * @return
     */
    @GetMapping
    private SPResult findUserByName(@RequestParam("username") String username) {
        SPResult result = userService.findUserByName(username);
        return result;
    }

    @GetMapping("/all")
    private SPResult findAllUsers() {
        SPResult result = userService.findAllUsers();
        return result;
    }

    @PostMapping("/addUser")
    private SPResult addUser(UserInfo user) {
        //account为前台页面传递过来的实体类,前台页面所定义的字段名需要与后台保持一致
        //将account存入数据库
        SPResult result = userService.addUser(user);
        return result;
    }

    @PostMapping("/checkPwd")
    private SPResult checkPassword(String username, String password) {
        SPResult result = userService.checkPasswordByUsername(username, password);
        return result;
    }

    @PostMapping("/updateUser")
    private SPResult updateUser(UserInfo newUser) {
        SPResult result = userService.updateUser(newUser);
        return result;
    }

    @GetMapping("/deleteUser")
    private boolean deleteUser(String username) {
        boolean result = userService.deleteUser(username);
        return result;
    }
}
