package com.yyh.wubida.service.truck.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.common.utils.Constant;
import com.yyh.wubida.mapper.truck.WbdTruckMapper;
import com.yyh.wubida.entity.truck.WbdTruck;
import com.yyh.wubida.service.truck.IWbdTruckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * <p>
 * 车辆信息表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdTruckServiceImpl extends ServiceImpl<WbdTruckMapper, WbdTruck> implements IWbdTruckService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public WbdTruck saveTruck(WbdTruck WbdTruck) {
        WbdTruck.setId(idGenerator.nextId(WbdTruck) + "");
        baseMapper.insert(WbdTruck);
        return WbdTruck;
    }

    @Override
    public IPage<WbdTruck> findByPage(Integer page, Integer pageSize, String truckTypeId, String licensePlate, String fleetId) {
        Page<WbdTruck> iPage = new Page(page, pageSize);
        LambdaQueryWrapper<WbdTruck> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(licensePlate)) {
            lambdaQueryWrapper.like(WbdTruck::getLicensePlate, licensePlate);
        }
        if (StringUtils.isNotEmpty(truckTypeId)) {
            lambdaQueryWrapper.eq(WbdTruck::getTruckTypeId, truckTypeId);

        }
        if (StringUtils.isNotEmpty(fleetId)) {
            lambdaQueryWrapper.eq(WbdTruck::getFleetId, fleetId);

        }
        lambdaQueryWrapper.eq(WbdTruck::getStatus, Constant.DATA_DEFAULT_STATUS);
        lambdaQueryWrapper.orderBy(true, false, WbdTruck::getId);
        return baseMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    @Override
    public List<WbdTruck> findAll(List<String> ids, String fleetId) {
        LambdaQueryWrapper<WbdTruck> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ids != null && ids.size() > 0) {
            lambdaQueryWrapper.in(WbdTruck::getId, ids);
        }
        if (StringUtils.isNotEmpty(fleetId)) {
            lambdaQueryWrapper.eq(WbdTruck::getFleetId, fleetId);
        }
        lambdaQueryWrapper.eq(WbdTruck::getStatus, Constant.DATA_DEFAULT_STATUS);
        lambdaQueryWrapper.orderBy(true, false, WbdTruck::getId);
        return baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public Integer count(String fleetId) {
        LambdaQueryWrapper<WbdTruck> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(fleetId)) {
            lambdaQueryWrapper.eq(WbdTruck::getId, fleetId);
        }
        lambdaQueryWrapper.eq(WbdTruck::getStatus, Constant.DATA_DEFAULT_STATUS);
        return baseMapper.selectCount(lambdaQueryWrapper);
    }

    @Override
    public void disableById(String id) {
        WbdTruck WbdTruck = new WbdTruck();
        WbdTruck.setId(id);
        WbdTruck.setStatus(Constant.DATA_DISABLE_STATUS);
        baseMapper.updateById(WbdTruck);
    }

}
