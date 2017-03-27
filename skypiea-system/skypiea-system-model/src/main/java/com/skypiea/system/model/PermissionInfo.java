package com.skypiea.system.model;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-27 13:43
 */
public class PermissionInfo {

    private int id;
    //权限名称:如增加用户,删除用户等
    private String name;
    //权限标识代码
    private String percode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode;
    }
}
