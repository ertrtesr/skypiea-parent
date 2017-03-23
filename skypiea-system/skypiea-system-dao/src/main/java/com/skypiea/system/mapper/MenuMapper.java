package com.skypiea.system.mapper;

import com.skypiea.system.model.MenuInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-13 16:31
 */

@Mapper
public interface MenuMapper {

    List<MenuInfo> findAllMenus();
}
