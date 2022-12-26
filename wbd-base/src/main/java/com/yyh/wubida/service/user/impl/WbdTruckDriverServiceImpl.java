package com.yyh.wubida.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.mapper.user.WbdTruckDriverMapper;
import com.yyh.wubida.entity.user.WbdTruckDriver;
import com.yyh.wubida.service.user.IWbdTruckDriverService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 司机表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdTruckDriverServiceImpl extends ServiceImpl<WbdTruckDriverMapper, WbdTruckDriver>
        implements IWbdTruckDriverService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public WbdTruckDriver saveTruckDriver(WbdTruckDriver wbdTruckDriver) {
        WbdTruckDriver driver = baseMapper.selectOne(new LambdaQueryWrapper<WbdTruckDriver>().eq(WbdTruckDriver::getUserId, wbdTruckDriver.getUserId()));
        if (driver == null) {
            wbdTruckDriver.setId(idGenerator.nextId(wbdTruckDriver) + "");
        } else {
            wbdTruckDriver.setId(driver.getId());
        }
        saveOrUpdate(wbdTruckDriver);
        return wbdTruckDriver;
    }

    @Override
    public List<WbdTruckDriver> findAll(List<String> userIds, String fleetId) {
        boolean hasUserIds = userIds != null && userIds.size() > 0;
        boolean hasFleetId = StringUtils.isNotEmpty(fleetId);
        if (!hasUserIds && !hasFleetId) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<WbdTruckDriver> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (hasUserIds) {
            lambdaQueryWrapper.in(WbdTruckDriver::getUserId, userIds);
        }
        if (hasFleetId) {
            lambdaQueryWrapper.eq(WbdTruckDriver::getFleetId, fleetId);
        }
        lambdaQueryWrapper.orderBy(true, false, WbdTruckDriver::getId);
        return baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public WbdTruckDriver findOne(String userId) {
        LambdaQueryWrapper<WbdTruckDriver> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(userId)) {
            lambdaQueryWrapper.eq(WbdTruckDriver::getUserId, userId);
        }
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public Integer count(String fleetId) {
        LambdaQueryWrapper<WbdTruckDriver> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(fleetId)) {
            lambdaQueryWrapper.eq(WbdTruckDriver::getFleetId, fleetId);
        }
        return count(lambdaQueryWrapper);
    }

    @Override
    public IPage<WbdTruckDriver> findByPage(Integer page, Integer pageSize, String fleetId) {
        Page<WbdTruckDriver> iPage = new Page(page, pageSize);
        LambdaQueryWrapper<WbdTruckDriver> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(fleetId)) {
            lambdaQueryWrapper.eq(WbdTruckDriver::getFleetId, fleetId);
        }
        lambdaQueryWrapper.orderBy(true, false, WbdTruckDriver::getId);
        return baseMapper.selectPage(iPage, lambdaQueryWrapper);
    }
}
