package com.yyh.wubida.mapper.transportline;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyh.wubida.entity.transportline.WbdTransportTripsTruckDriver;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 车次与车辆关联信息表  Mapper 接口
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Mapper
public interface WbdTransportTripsTruckDriverMapper extends BaseMapper<WbdTransportTripsTruckDriver> {

}
