package com.yyh.wubida.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.mapper.user.WbdTruckDriverLicenseMapper;
import com.yyh.wubida.entity.user.WbdTruckDriverLicense;
import com.yyh.wubida.service.user.IWbdTruckDriverLicenseService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机驾驶证表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdTruckDriverLicenseServiceImpl extends ServiceImpl<WbdTruckDriverLicenseMapper, WbdTruckDriverLicense> implements IWbdTruckDriverLicenseService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public WbdTruckDriverLicense saveTruckDriverLicense(WbdTruckDriverLicense wbdTruckDriverLicense) {
        WbdTruckDriverLicense driverLicense = baseMapper.selectOne(new LambdaQueryWrapper<WbdTruckDriverLicense>().eq(WbdTruckDriverLicense::getUserId, wbdTruckDriverLicense.getUserId()));
        if (driverLicense == null) {
            wbdTruckDriverLicense.setId(idGenerator.nextId(wbdTruckDriverLicense) + "");
        } else {
            wbdTruckDriverLicense.setId(driverLicense.getId());
        }
        saveOrUpdate(wbdTruckDriverLicense);
        return wbdTruckDriverLicense;
    }

    @Override
    public WbdTruckDriverLicense findOne(String userId) {
        LambdaQueryWrapper<WbdTruckDriverLicense> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(userId)) {
            lambdaQueryWrapper.eq(WbdTruckDriverLicense::getUserId, userId);
        }
        return getOne(lambdaQueryWrapper);
    }

}
