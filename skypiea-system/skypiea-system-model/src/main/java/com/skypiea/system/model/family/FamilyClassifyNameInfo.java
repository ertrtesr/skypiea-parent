package com.skypiea.system.model.family;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-04-21 10:10
 */

@Getter
@Setter
public class FamilyClassifyNameInfo {
    private Long id;
    private String classify;
    private Long parentId;
    private List<FamilyClassifyNameInfo> children;
}
