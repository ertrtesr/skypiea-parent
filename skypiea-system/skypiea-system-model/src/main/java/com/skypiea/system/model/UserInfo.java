package com.skypiea.system.model;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-02-20 18:52
 */

public class UserInfo {

    private int id;
    private String username;
    private String password;
    private String authorization;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
