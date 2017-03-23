package com.skypiea.system.service.impl;

import com.skypiea.system.mapper.MenuMapper;
import com.skypiea.system.model.MenuInfo;
import com.skypiea.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: huangwenjian
 * 描述: parentId=0的为根节点
 * 创建时间: 2017-03-13 16:46
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    private List<MenuInfo> menus;

    @Override
    public List<MenuInfo> findAllMenus() {

        menus = menuMapper.findAllMenus();
        ArrayList<MenuInfo> menuList = new ArrayList<>();
        for (MenuInfo menu : menus) {
            //根据menuId查找是否有子节点,有的话就设置到每个menu对象中
            List<MenuInfo> childList = getChildList(menu.getMenuId());
            menu.setChildList(childList);
            if (menu.getMenuParentId() == 0) {  //parentId=0代表是根节点时才添加到集合中
                menuList.add(menu);
            }
        }
        return menuList;
    }

    /**
     * 根据menuId查找是否有子节点
     *
     * @param menuId
     * @return
     */
    public List<MenuInfo> getChildList(int menuId) {
        List<MenuInfo> childList = new ArrayList<>();
        //遍历menu列表
        for (MenuInfo menu : menus) {
            if (menuId == menu.getMenuParentId()) {
                childList.add(menu);        //menu条目的parentId等于传入的menuId时,将其添加到childList集合中
                if (!(menu.getIsLeaf() == "true")) {    //如果不是叶子节点,则继续递归遍历menu下面的子节点
                    getChildList(menu.getMenuId());
                }
            }
        }
        return childList;
    }
}
