package com.yyh.wubida.service.transportline;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.transportline.WbdTransportTrips;

import java.util.List;

/**
 * <p>
 * 车次信息表 服务类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
public interface IWbdTransportTripsService extends IService<WbdTransportTrips> {
    /**
     * 添加车次
     *
     * @param WbdTransportTrips 车次信息
     * @return 车次信息
     */
    WbdTransportTrips saveTransportTrips(WbdTransportTrips WbdTransportTrips);

    /**
     * 获取车次列表
     *
     * @param transportLineId 线路id
     * @param ids             车次id列表
     * @return 车次列表
     */
    List<WbdTransportTrips> findAll(String transportLineId, List<String> ids);

    /**
     * 删除车次
     *
     * @param id
     */
    void disable(String id);
}
