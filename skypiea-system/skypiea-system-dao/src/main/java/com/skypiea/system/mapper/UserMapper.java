package com.skypiea.system.mapper;

import com.skypiea.system.model.PermissionInfo;
import com.skypiea.system.model.RoleInfo;
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

    //通过用户id查询用户角色
    List<RoleInfo> findRolesByUserId(int id);

    //通过用户名查询用户角色
    List<RoleInfo> findRolesByUsername(String username);

    //通过用户id查询用户权限
    List<PermissionInfo> findPermissionsByUserId(int id);

    //通过用户名查询用户权限
    List<PermissionInfo> findPermissionsByUsername(String username);

    List<UserInfo> findAllUsers();

    /**
     * 根据用户名查找密码
     *
     * @param username
     * @return
     */
    String findPasswordByUsername(String username);

    /**
     * 往t_user表中添加用户,返回自增长ID
     *
     * @param user
     */
    void addUser(UserInfo user);

    /**
     * 往t_user_role关系表中添加用户id,用户名,角色id
     *
     * @param user
     */
    void addUserRole(UserInfo user);

    /**
     * 更新用户
     *
     * @param newUser
     */
    void updateUser(UserInfo newUser);

    /**
     * 根据用户名更新t_user_role关系表中的角色id
     *
     * @param username
     */
    void updateUserRole(String username);

    /**
     * 根据用户名删除用户
     *
     * @param username
     */
    void deleteUser(String username);
}
