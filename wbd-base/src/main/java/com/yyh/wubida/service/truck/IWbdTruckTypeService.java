package com.yyh.wubida.service.truck;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.truck.WbdTruckType;

/**
 * <p>
 * 车辆类型表 服务类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
public interface IWbdTruckTypeService extends IService<WbdTruckType> {
    /**
     * 添加车辆类型
     *
     * @param WbdTruckType 车辆类型信息
     * @return 车辆类型信息
     */
    WbdTruckType saveTruckType(WbdTruckType WbdTruckType);

    /**
     * 获取车辆类型分页数据
     *
     * @param page            页码
     * @param pageSize        页尺寸
     * @param name            类型名称
     * @param allowableLoad   车型载重
     * @param allowableVolume 车型体积
     * @return 线路类型分页数据
     */
    IPage<WbdTruckType> findByPage(Integer page, Integer pageSize, String name, BigDecimal allowableLoad,
                                  BigDecimal allowableVolume);

    /**
     * 获取车辆类型列表
     * @return 车辆类型列表
     */
    List<WbdTruckType> findAll(List<String> ids);
}
