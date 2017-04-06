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
    private SPResult getUserById(@PathVariable int id) {
        SPResult result = userService.getUserById(id);
        return result;
    }

    /**
     * RequestParam注解相当于给username取了一个别名
     *
     * @param username
     * @return
     */
    @GetMapping
    private SPResult getUserByName(@RequestParam("username") String username) {
        SPResult result = userService.getUserByName(username);
        return result;
    }

    @GetMapping("/all")
    private SPResult getAllUsers() {
        SPResult result = userService.getAllUsers();
        return result;
    }

    @GetMapping("/all/{pageNum}")
    private SPResult getUsers(@PathVariable int pageNum, int pageSize) {
        SPResult result = userService.getUsers(pageNum, pageSize);
        return result;
    }

    /**
     * 获取用户数量
     *
     * @return
     */
    @GetMapping("/count")
    private int getUserCount() {
        int count = userService.getUserCount();
        return count;
    }

    @PostMapping("/addUser")
    private SPResult addUser(UserInfo user) {
        SPResult result = userService.addUser(user);
        return result;
    }

    @PostMapping("/checkPwd")
    private SPResult checkPassword(String username, String password) {
        SPResult result = userService.checkPasswordByUsername(username, password);
        return result;
    }

    @PostMapping("/updateUser")
    private SPResult updateUser(UserInfo user) {
        SPResult result = userService.updateUser(user);
        return result;
    }

    @GetMapping("/deleteUser")
    private boolean deleteUser(String username) {
        boolean result = userService.deleteUser(username);
        return result;
    }
}
