package com.skypiea.system.model.family;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 作者: huangwenjian
 * 描述: 族信息
 * 创建时间: 2017-04-20 14:30
 */

@Getter
@Setter
public class FamilyInfo {
    private Long id;
    private String name;
    private String uploader;
    private Integer downloadCount;
    private Date createTime;
    private Date updateTime;
    private String imgUrl;
    private FamilyClassifyInfo familyClassifyInfo;
}
