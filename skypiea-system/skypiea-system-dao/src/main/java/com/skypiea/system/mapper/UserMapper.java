package com.skypiea.system.mapper;

import com.skypiea.system.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-02-20 18:52
 */

@Mapper
public interface UserMapper {

    UserInfo findUserById(int id);

    UserInfo findUserByName(String username);

    UserInfo findUserByPhone(String phone);

    UserInfo findUserByEmail(String email);

    List<UserInfo> findAllUsers();

    /**
     * 根据用户名查找密码
     *
     * @param username
     * @return
     */
    String findPasswordByUsername(String username);

    /**
     * 添加用户,返回自增长ID
     *
     * @param user
     */
    void addUser(UserInfo user);

    /**
     * 更新用户
     *
     * @param newUser
     */
    void updateUser(UserInfo newUser);

    /**
     * 根据用户名删除用户
     *
     * @param username
     */
    void deleteUser(String username);
}
