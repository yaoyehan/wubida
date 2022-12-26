package com.yyh.wubida.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.user.WbdCourierScope;

import java.util.List;

/**
 * <p>
 * 快递员业务范围表  服务类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
public interface IWbdCourierScopeService extends IService<WbdCourierScope> {
    /**
     * 批量保存快递员业务范围
     *
     * @param scopeList 快递员业务范围信息列表
     */
    void batchSave(List<WbdCourierScope> scopeList);

    /**
     * 删除快递员业务范围
     *
     * @param areaId 行政区域id
     * @param userId 快递员id
     */
    void delete(String areaId, String userId);

    List<WbdCourierScope> findAll(String areaId, String userId);
}
