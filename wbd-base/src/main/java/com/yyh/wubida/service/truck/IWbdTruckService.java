package com.yyh.wubida.service.truck;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.truck.WbdTruck;

import java.util.List;

/**
 * <p>
 * 车辆信息表 服务类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
public interface IWbdTruckService extends IService<WbdTruck> {
    /**
     * 添加车辆
     *
     * @param WbdTruck 车辆信息
     * @return 车辆信息
     */
    WbdTruck saveTruck(WbdTruck WbdTruck);

    /**
     * 获取车辆分页数据
     *
     * @param page         页码
     * @param pageSize     页尺寸
     * @param truckTypeId  车辆类型id
     * @param licensePlate 车辆号牌
     * @return 线路类型分页数据
     */
    IPage<WbdTruck> findByPage(Integer page, Integer pageSize, String truckTypeId, String licensePlate, String fleetId);

    /**
     * 获取车辆列表
     *
     * @param ids     车辆id列表
     * @param fleetId 车队id
     * @return 车辆列表
     */
    List<WbdTruck> findAll(List<String> ids, String fleetId);

    /**
     * 统计车辆数量
     *
     * @param fleetId 车队id
     * @return 车辆数量
     */
    Integer count(String fleetId);

    /**
     * 删除车辆
     *
     * @param id
     */
    void disableById(String id);
}
