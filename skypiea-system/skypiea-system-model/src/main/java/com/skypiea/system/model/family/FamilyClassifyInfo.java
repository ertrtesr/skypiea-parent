package com.skypiea.system.model.family;

import lombok.Getter;
import lombok.Setter;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-04-20 14:47
 */

@Getter
@Setter
public class FamilyClassifyInfo {

    private GeometricAttributeInfo geometricAttributeInfo;
    private PhysicalAttributeInfo physicalAttributeInfo;
    private ConstructionAttributeInfo constructionAttributeInfo;
    private CalculationAttributeInfo calculationAttributeInfo;
    private BusinessAttributeInfo businessAttributeInfo;
    private RepairAttributeInfo repairAttributeInfo;
    private OtherAttributeInfo otherAttributeInfo;
}
