package com.yyh.wubida.service.truck;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.truck.WbdTruckLicense;

/**
 * <p>
 * 车辆行驶证表  服务类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
public interface IWbdTruckLicenseService extends IService<WbdTruckLicense> {
    /**
     * 保存车辆行驶证信息
     *
     * @param WbdTruckLicense 车辆行驶证信息
     * @return 车辆行驶证信息
     */
    WbdTruckLicense saveTruckLicense(WbdTruckLicense WbdTruckLicense);
}
