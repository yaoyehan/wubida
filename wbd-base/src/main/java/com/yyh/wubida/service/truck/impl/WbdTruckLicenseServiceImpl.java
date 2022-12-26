package com.yyh.wubida.service.truck.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.mapper.truck.WbdTruckLicenseMapper;
import com.yyh.wubida.entity.truck.WbdTruck;
import com.yyh.wubida.entity.truck.WbdTruckLicense;
import com.yyh.wubida.service.truck.IWbdTruckLicenseService;
import com.yyh.wubida.service.truck.IWbdTruckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车辆行驶证表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdTruckLicenseServiceImpl extends ServiceImpl<WbdTruckLicenseMapper, WbdTruckLicense>
        implements IWbdTruckLicenseService {
    @Autowired
    private CustomIdGenerator idGenerator;
    @Autowired
    private IWbdTruckService truckService;

    @Override
    public WbdTruckLicense saveTruckLicense(WbdTruckLicense WbdTruckLicense) {
        if (WbdTruckLicense.getId() == null) {
            WbdTruckLicense.setId(idGenerator.nextId(WbdTruckLicense) + "");
            baseMapper.insert(WbdTruckLicense);
            // 处理车辆信息中的关联字段
            if (WbdTruckLicense.getTruckId() != null) {
                WbdTruck WbdTruck = truckService.getById(WbdTruckLicense.getTruckId());
                WbdTruck.setTruckLicenseId(WbdTruckLicense.getId());
                truckService.updateById(WbdTruck);
            }
        } else {
            baseMapper.updateById(WbdTruckLicense);
        }
        return WbdTruckLicense;
    }

}
