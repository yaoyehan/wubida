package com.yyh.wubida.service.truck.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.mapper.truck.WbdTruckTypeGoodsTypeMapper;
import com.yyh.wubida.entity.truck.WbdTruckTypeGoodsType;
import com.yyh.wubida.service.truck.IWbdTruckTypeGoodsTypeService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 车辆类型与货物类型关联表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdTruckTypeGoodsTypeServiceImpl extends ServiceImpl<WbdTruckTypeGoodsTypeMapper, WbdTruckTypeGoodsType>
        implements IWbdTruckTypeGoodsTypeService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public void saveTruckTypeGoodsType(WbdTruckTypeGoodsType WbdTruckTypeGoodsType) {
        WbdTruckTypeGoodsType.setId(idGenerator.nextId(WbdTruckTypeGoodsType) + "");
        baseMapper.insert(WbdTruckTypeGoodsType);
    }

    @Override
    public void batchSave(List<WbdTruckTypeGoodsType> truckTypeGoodsTypeList) {
        truckTypeGoodsTypeList.forEach(WbdTruckTypeGoodsType -> WbdTruckTypeGoodsType.setId(idGenerator.nextId(WbdTruckTypeGoodsType) + ""));
        saveBatch(truckTypeGoodsTypeList);
    }

    @Override
    public void delete(String truckTypeId, String goodsTypeId) {
        LambdaQueryWrapper<WbdTruckTypeGoodsType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        boolean canExecute = false;
        if (StringUtils.isNotEmpty(truckTypeId)) {
            lambdaQueryWrapper.eq(WbdTruckTypeGoodsType::getTruckTypeId, truckTypeId);
            canExecute = true;
        }
        if (StringUtils.isNotEmpty(goodsTypeId)) {
            lambdaQueryWrapper.eq(WbdTruckTypeGoodsType::getGoodsTypeId, goodsTypeId);
            canExecute = true;
        }
        if (canExecute) {
            baseMapper.delete(lambdaQueryWrapper);
        }
    }

    @Override
    public List<WbdTruckTypeGoodsType> findAll(String truckTypeId, String goodsTypeId) {
        LambdaQueryWrapper<WbdTruckTypeGoodsType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(truckTypeId)) {
            lambdaQueryWrapper.eq(WbdTruckTypeGoodsType::getTruckTypeId, truckTypeId);
        }
        if (StringUtils.isNotEmpty(goodsTypeId)) {
            lambdaQueryWrapper.eq(WbdTruckTypeGoodsType::getGoodsTypeId, goodsTypeId);
        }
        return baseMapper.selectList(lambdaQueryWrapper);
    }

}
