package com.yyh.wubida.service.agency.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.common.utils.Constant;
import com.yyh.wubida.mapper.agency.WbdFleetMapper;
import com.yyh.wubida.entity.agency.WbdFleet;
import com.yyh.wubida.service.agency.IWbdFleetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * 车队表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdFleetServiceImpl extends ServiceImpl<WbdFleetMapper, WbdFleet> implements IWbdFleetService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public WbdFleet saveFleet(WbdFleet fleet) {
        fleet.setId(idGenerator.nextId(fleet) + "");
        baseMapper.insert(fleet);
        return fleet;
    }

    @Override
    public IPage<WbdFleet> findByPage(Integer page, Integer pageSize, String name, String fleetNumber, String manager) {
        Page<WbdFleet> iPage = new Page(page, pageSize);
        LambdaQueryWrapper<WbdFleet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            lambdaQueryWrapper.like(WbdFleet::getName, name);
        }
        if (StringUtils.isNotEmpty(fleetNumber)) {
            lambdaQueryWrapper.like(WbdFleet::getFleetNumber, fleetNumber);
        }
        if (StringUtils.isNotEmpty(manager)) {
            lambdaQueryWrapper.eq(WbdFleet::getManager, manager);
        }
        lambdaQueryWrapper.eq(WbdFleet::getStatus, Constant.DATA_DEFAULT_STATUS);
        lambdaQueryWrapper.orderBy(true, true, WbdFleet::getId);
        return baseMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    @Override
    public List<WbdFleet> findAll(List<String> ids, String agencyId) {
        LambdaQueryWrapper<WbdFleet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ids != null && ids.size() > 0) {
            lambdaQueryWrapper.in(WbdFleet::getId, ids);
        }
        if (StringUtils.isNotEmpty(agencyId)) {
            lambdaQueryWrapper.eq(WbdFleet::getAgencyId, agencyId);
        }
        lambdaQueryWrapper.orderBy(true, true, WbdFleet::getId);
        lambdaQueryWrapper.eq(WbdFleet::getStatus, Constant.DATA_DEFAULT_STATUS);
        return baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void disableById(String id) {
        WbdFleet fleet = new WbdFleet();
        fleet.setId(id);
        fleet.setStatus(Constant.DATA_DISABLE_STATUS);
        baseMapper.updateById(fleet);
    }

}
