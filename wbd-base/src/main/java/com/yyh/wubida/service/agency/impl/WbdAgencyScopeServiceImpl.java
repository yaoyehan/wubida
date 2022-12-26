package com.yyh.wubida.service.agency.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.entity.agency.WbdAgencyScope;
import com.yyh.wubida.mapper.agency.WbdAgencyScopMapper;
import com.yyh.wubida.service.agency.IWbdAgencyScopeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 机构业务范围表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdAgencyScopeServiceImpl extends ServiceImpl<WbdAgencyScopMapper, WbdAgencyScope> implements IWbdAgencyScopeService {
    @Autowired
    private CustomIdGenerator idGenerator;


    @Override
    public void batchSave(List<WbdAgencyScope> scopeList) {
        scopeList.forEach(scope -> scope.setId(idGenerator.nextId(scope) + ""));
        saveBatch(scopeList);
    }

    @Override
    public void delete(String areaId, String agencyId) {
        LambdaQueryWrapper<WbdAgencyScope> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        boolean canExecute = false;
        if (StringUtils.isNotEmpty(areaId)) {
            lambdaQueryWrapper.eq(WbdAgencyScope::getAreaId, areaId);
            canExecute = true;
        }
        if (StringUtils.isNotEmpty(agencyId)) {
            lambdaQueryWrapper.eq(WbdAgencyScope::getAgencyId, agencyId);
            canExecute = true;
        }
        if (canExecute) {
            baseMapper.delete(lambdaQueryWrapper);
        }
    }

    @Override
    public List<WbdAgencyScope> findAll(String areaId, String agencyId, List<String> agencyIds, List<String> areaIds) {
        LambdaQueryWrapper<WbdAgencyScope> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(areaId)) {
            lambdaQueryWrapper.eq(WbdAgencyScope::getAreaId, areaId);
        }
        if (StringUtils.isNotEmpty(agencyId)) {
            lambdaQueryWrapper.eq(WbdAgencyScope::getAgencyId, agencyId);
        }
        if (agencyIds != null && agencyIds.size() > 0) {
            lambdaQueryWrapper.in(WbdAgencyScope::getAgencyId, agencyIds);
        }
        if (areaIds != null && areaIds.size() > 0) {
            lambdaQueryWrapper.in(WbdAgencyScope::getAreaId, areaIds);
        }
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
