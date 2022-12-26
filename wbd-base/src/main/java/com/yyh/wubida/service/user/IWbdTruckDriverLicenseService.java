package com.yyh.wubida.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.user.WbdTruckDriverLicense;

/**
 * <p>
 * 司机驾驶证表  服务类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
public interface IWbdTruckDriverLicenseService extends IService<WbdTruckDriverLicense> {
    /**
     * 保存司机驾驶证信息
     *
     * @param WbdTruckDriverLicense 司机驾驶证信息
     * @return 司机驾驶证信息
     */
    WbdTruckDriverLicense saveTruckDriverLicense(WbdTruckDriverLicense WbdTruckDriverLicense);

    /**
     * 获取司机驾驶证信息
     *
     * @param userId 司机id
     * @return 司机驾驶证信息
     */
    WbdTruckDriverLicense findOne(String userId);
}
