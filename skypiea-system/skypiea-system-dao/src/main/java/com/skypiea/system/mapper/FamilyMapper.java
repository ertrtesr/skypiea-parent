package com.skypiea.system.mapper;

import com.skypiea.system.model.family.FamilyClassifyNameInfo;
import com.skypiea.system.model.family.FamilyInfo;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-04-21 10:22
 */

public interface FamilyMapper {

    List<FamilyInfo> getAllFamilies();

    FamilyInfo getFamilyById(Long id);

    List<FamilyClassifyNameInfo> getAllFamilyClassifies();
}
