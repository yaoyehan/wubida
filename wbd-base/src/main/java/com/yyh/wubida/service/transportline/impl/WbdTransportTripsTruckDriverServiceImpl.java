package com.yyh.wubida.service.transportline.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.entity.transportline.WbdTransportTripsTruckDriver;
import com.yyh.wubida.mapper.transportline.WbdTransportTripsTruckDriverMapper;
import com.yyh.wubida.service.transportline.IWbdTransportTripsTruckDriverService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车次与车辆关联信息表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdTransportTripsTruckDriverServiceImpl extends ServiceImpl<WbdTransportTripsTruckDriverMapper, WbdTransportTripsTruckDriver>
        implements IWbdTransportTripsTruckDriverService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public void batchSave(String truckTransportTripsId, List<WbdTransportTripsTruckDriver> truckTransportTripsTruckDriverList) {
        LambdaQueryWrapper<WbdTransportTripsTruckDriver> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(WbdTransportTripsTruckDriver::getTransportTripsId, truckTransportTripsId);
        //查出操作前关系列表
        List<WbdTransportTripsTruckDriver> transportTripsTruckDriverList = baseMapper.selectList(lambdaQueryWrapper);
        Map<String, WbdTransportTripsTruckDriver> sourceTruckKeyMap = new HashMap<>();
        for (WbdTransportTripsTruckDriver WbdTransportTripsTruckDriver:transportTripsTruckDriverList){
            sourceTruckKeyMap.put(WbdTransportTripsTruckDriver.getTransportTripsId() + "_" + WbdTransportTripsTruckDriver.getTruckId(),WbdTransportTripsTruckDriver);
        }
        //清除关系
        baseMapper.delete(lambdaQueryWrapper);
        List<WbdTransportTripsTruckDriver> saveList = new ArrayList<>();
        //遍历传入数据
        truckTransportTripsTruckDriverList.forEach(WbdTransportTripsTruckDriver -> {
            WbdTransportTripsTruckDriver saveData = new WbdTransportTripsTruckDriver();
            BeanUtils.copyProperties(WbdTransportTripsTruckDriver, saveData);
            saveData.setId(idGenerator.nextId(saveData) + "");
            if (sourceTruckKeyMap.containsKey(WbdTransportTripsTruckDriver.getTransportTripsId() + "_" + WbdTransportTripsTruckDriver.getTruckId())) {
                WbdTransportTripsTruckDriver source = sourceTruckKeyMap.get(WbdTransportTripsTruckDriver.getTransportTripsId() + "_" + WbdTransportTripsTruckDriver.getTruckId());
                if (saveData.getUserId() == null) {
                    saveData.setUserId(source.getUserId());
                }
            }
            saveList.add(saveData);
        });
        saveBatch(saveList);
    }

    @Override
    public List<WbdTransportTripsTruckDriver> findAll(String transportTripsId, String truckId, String userId) {
        LambdaQueryWrapper<WbdTransportTripsTruckDriver> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(transportTripsId)) {
            lambdaQueryWrapper.eq(WbdTransportTripsTruckDriver::getTransportTripsId, transportTripsId);
        }
        if (StringUtils.isNotEmpty(truckId)) {
            lambdaQueryWrapper.eq(WbdTransportTripsTruckDriver::getTruckId, truckId);
        }
        if (StringUtils.isNotEmpty(userId)) {
            lambdaQueryWrapper.eq(WbdTransportTripsTruckDriver::getUserId, userId);
        }
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
