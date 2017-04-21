package com.skypiea.system.model.family;

import lombok.Getter;
import lombok.Setter;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-04-20 15:22
 */

@Getter
@Setter
public class CalculationAttributeInfo {
    //    安全系数(safety-coefficient) 摩擦系数(friction-coefficient)
//    风力系数(wind-coefficient) 刚度系数(stiffness coefficient)
    private Double safetyCoefficient;
    private Double frictionCoefficient;
    private Double windCoefficient;
    private Double stiffnessCoefficient;
}
