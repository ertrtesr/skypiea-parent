package com.skypiea.system.service.impl;

import com.skypiea.system.mapper.FamilyMapper;
import com.skypiea.system.model.family.FamilyClassifyNameInfo;
import com.skypiea.system.model.family.FamilyInfo;
import com.skypiea.system.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-04-21 10:31
 */

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyMapper familyMapper;

    @Override
    public List<FamilyInfo> getAllFamilies() {
        List<FamilyInfo> families = familyMapper.getAllFamilies();
        return families;
    }

    @Override
    public FamilyInfo getFamilyById(Long id) {
        return familyMapper.getFamilyById(id);
    }

    @Override
    public List<FamilyClassifyNameInfo> getAllFamilyClassifies() {
        List<FamilyClassifyNameInfo> classifies = familyMapper.getAllFamilyClassifies();

        //获取根元素
        List<FamilyClassifyNameInfo> roots = classifies.stream().filter(classify -> (classify.getParentId() == 0)).collect(Collectors.toList());
        //获取parentId!=0的所有非根元素
        List<FamilyClassifyNameInfo> subs = classifies.stream().filter(classify -> (classify.getParentId() != 0)).collect(Collectors.toList());

        for (FamilyClassifyNameInfo root : roots) {
            buildSubs(root, subs);
        }
        return roots;
    }

    private void buildSubs(FamilyClassifyNameInfo root, List<FamilyClassifyNameInfo> subs) {
        //遍历所有的子元素
        //如果子元素的parentId==父元素的id,将其过滤出来
        List<FamilyClassifyNameInfo> children = subs.stream().filter(sub -> (sub.getParentId() == root.getId())).collect(Collectors.toList());
        root.setChildren(children);
        for (FamilyClassifyNameInfo child : children) {
            buildSubs(child, subs);
        }
    }
}
