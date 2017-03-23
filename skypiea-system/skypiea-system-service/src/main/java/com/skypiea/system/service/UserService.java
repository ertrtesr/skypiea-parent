package com.skypiea.system.service;

import com.skypiea.common.result.SPResult;
import com.skypiea.system.model.UserInfo;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-02-20 19:03
 */
public interface UserService {

    SPResult findUserById(int id);

    SPResult findUserByName(String username);

    SPResult findAllUsers();

    SPResult checkPasswordByUsername(String username, String password);

    SPResult addUser(UserInfo newUser);

    /**
     * 更新用户
     *
     * @param newUser 新的用户,但是id和username是不能更改的
     * @return
     */
    SPResult updateUser(UserInfo newUser);

    boolean deleteUser(String username);
}
