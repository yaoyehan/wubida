package com.yyh.wubida.service.transportline.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.common.utils.Constant;
import com.yyh.wubida.mapper.transportline.WbdTransportTripsMapper;
import com.yyh.wubida.entity.transportline.WbdTransportTrips;
import com.yyh.wubida.service.transportline.IWbdTransportTripsService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 车次信息表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdTransportTripsServiceImpl extends ServiceImpl<WbdTransportTripsMapper, WbdTransportTrips>
        implements IWbdTransportTripsService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public WbdTransportTrips saveTransportTrips(WbdTransportTrips WbdTransportTrips) {
        WbdTransportTrips.setId(idGenerator.nextId(WbdTransportTrips) + "");
        baseMapper.insert(WbdTransportTrips);
        return WbdTransportTrips;
    }

    @Override
    public List<WbdTransportTrips> findAll(String transportLineId, List<String> ids) {
        LambdaQueryWrapper<WbdTransportTrips> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(transportLineId)) {
            lambdaQueryWrapper.eq(WbdTransportTrips::getTransportLineId, transportLineId);
        }
        if (ids != null && ids.size() > 0) {
            lambdaQueryWrapper.in(WbdTransportTrips::getId, ids);
        }
        lambdaQueryWrapper.orderBy(true, true, WbdTransportTrips::getId);
        lambdaQueryWrapper.eq(WbdTransportTrips::getStatus, Constant.DATA_DEFAULT_STATUS);
        return baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void disable(String id) {
        WbdTransportTrips WbdTransportTrips = new WbdTransportTrips();
        WbdTransportTrips.setId(id);
        WbdTransportTrips.setStatus(Constant.DATA_DISABLE_STATUS);
        baseMapper.updateById(WbdTransportTrips);
    }

}
