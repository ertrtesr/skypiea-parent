package com.skypiea.system.controller;

import com.skypiea.common.result.SPResult;
import com.skypiea.system.model.family.FamilyClassifyNameInfo;
import com.skypiea.system.model.family.FamilyInfo;
import com.skypiea.system.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-04-21 13:22
 */

@RestController
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @GetMapping("/sys/family/all")
    private SPResult getAllFamilies() {
        List<FamilyInfo> families = familyService.getAllFamilies();
        return SPResult.ok(families);
    }

    @GetMapping("/sys/family")
    private SPResult getFamilyById(Long id) {
        FamilyInfo family = familyService.getFamilyById(id);
        return SPResult.ok(family);
    }

    @GetMapping("/sys/family/allClassifies")
    private SPResult getAllFamilyClassifies() {
        List<FamilyClassifyNameInfo> classifies = familyService.getAllFamilyClassifies();
        return SPResult.ok(classifies);
    }
}
