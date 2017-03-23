package com.skypiea.system.controller;

import com.skypiea.system.model.MenuInfo;
import com.skypiea.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-13 16:36
 */

@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/all")
    public List<MenuInfo> findAllMenus() {
        List<MenuInfo> menus = menuService.findAllMenus();
        return menus;
    }
}
