package com.yyh.wubida.service.truck.impl;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.common.utils.Constant;
import com.yyh.wubida.mapper.truck.WbdTruckTypeMapper;
import com.yyh.wubida.entity.truck.WbdTruckType;
import com.yyh.wubida.service.truck.IWbdTruckTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * 车辆类型表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdTruckTypeServiceImpl extends ServiceImpl<WbdTruckTypeMapper, WbdTruckType> implements IWbdTruckTypeService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public WbdTruckType saveTruckType(WbdTruckType WbdTruckType) {
        WbdTruckType.setId(idGenerator.nextId(WbdTruckType) + "");
        baseMapper.insert(WbdTruckType);
        return WbdTruckType;
    }

    @Override
    public IPage<WbdTruckType> findByPage(Integer page, Integer pageSize, String name, BigDecimal allowableLoad,
                                         BigDecimal allowableVolume) {
        Page<WbdTruckType> iPage = new Page(page, pageSize);
        LambdaQueryWrapper<WbdTruckType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            lambdaQueryWrapper.like(WbdTruckType::getName, name);
        }
        if (allowableLoad != null) {
            lambdaQueryWrapper.eq(WbdTruckType::getAllowableLoad, allowableLoad);
        }
        if (allowableVolume != null) {
            lambdaQueryWrapper.eq(WbdTruckType::getAllowableVolume, allowableVolume);
        }
        lambdaQueryWrapper.eq(WbdTruckType::getStatus, Constant.DATA_DEFAULT_STATUS);
        return baseMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    @Override
    public List<WbdTruckType> findAll(List<String> ids) {
        LambdaQueryWrapper<WbdTruckType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ids != null && ids.size() > 0) {
            lambdaQueryWrapper.in(WbdTruckType::getId, ids);
        }
        lambdaQueryWrapper.eq(WbdTruckType::getStatus, Constant.DATA_DEFAULT_STATUS);
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
