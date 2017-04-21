package com.skypiea.system.model.family;

import lombok.Getter;
import lombok.Setter;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-04-20 15:25
 */

@Getter
@Setter
public class RepairAttributeInfo {

    //    使用年限(service-life) 检修间隔(repair-interval)
    private Integer serviceLife;
    private Integer repairInterval;
}
