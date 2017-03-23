package com.skypiea.system.service;

import com.skypiea.system.model.MenuInfo;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-13 16:36
 */
public interface MenuService {

    List<MenuInfo> findAllMenus();
}
