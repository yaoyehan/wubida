package com.yyh.wubida.service.truck;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.truck.WbdTruckTypeGoodsType;

import java.util.List;

/**
 * <p>
 * 车辆类型与货物类型关联表 服务类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
public interface IWbdTruckTypeGoodsTypeService extends IService<WbdTruckTypeGoodsType> {
    /**
     * 添加车辆类型与货物类型关联
     *
     * @param WbdTruckTypeGoodsType 车辆类型与货物类型信息
     */
    void saveTruckTypeGoodsType(WbdTruckTypeGoodsType WbdTruckTypeGoodsType);

    /**
     * 批量添加车辆类型与货物类型关联
     *
     * @param truckTypeGoodsTypeList 车辆类型与货物类型信息
     */
    void batchSave(List<WbdTruckTypeGoodsType> truckTypeGoodsTypeList);

    /**
     * 删除关联关系
     *
     * @param truckTypeId 车辆类型id
     * @param goodsTypeId 货物类型id
     */
    void delete(String truckTypeId, String goodsTypeId);

    /**
     * 获取车辆类型与货物类型关联
     *
     * @param truckTypeId 车辆类型id
     * @param goodsTypeId 货物类型id
     * @return 车辆类型与货物类型关联
     */
    List<WbdTruckTypeGoodsType> findAll(String truckTypeId, String goodsTypeId);
}
