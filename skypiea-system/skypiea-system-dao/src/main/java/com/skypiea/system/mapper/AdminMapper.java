package com.skypiea.system.mapper;

import com.skypiea.system.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-29 22:41
 */

@Mapper
public interface AdminMapper {

    UserInfo findAdminByName(String username);

    String findPasswordByUsername(String username);
}
