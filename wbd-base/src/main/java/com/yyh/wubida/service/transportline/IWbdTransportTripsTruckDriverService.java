package com.yyh.wubida.service.transportline;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.transportline.WbdTransportTripsTruckDriver;

import java.util.List;

/**
 * <p>
 * 车次与车辆关联信息表 服务类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
public interface IWbdTransportTripsTruckDriverService extends IService<WbdTransportTripsTruckDriver> {
    /**
     * 批量保存车次与车辆关联信息
     *
     * @param truckTransportTrips 车次与车辆关联信息
     */
    void batchSave(String truckTransportTripsId, List<WbdTransportTripsTruckDriver> truckTransportTrips);

    /**
     * 获取车次与车辆关联列表
     *
     * @param transportTripsId 车次id
     * @param truckId          车辆Id
     * @param userId           司机id
     * @return 车次与车辆关联列表
     */
    List<WbdTransportTripsTruckDriver> findAll(String transportTripsId, String truckId, String userId);
}
