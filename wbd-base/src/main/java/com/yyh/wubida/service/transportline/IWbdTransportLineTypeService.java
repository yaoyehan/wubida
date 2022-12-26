package com.yyh.wubida.service.transportline;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.transportline.WbdTransportLineType;

import java.util.List;

/**
 * <p>
 * 线路类型表 服务类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
public interface IWbdTransportLineTypeService extends IService<WbdTransportLineType> {
    /**
     * 添加线路类型
     *
     * @param WbdTransportLineType 线路类型信息
     * @return 线路类型信息
     */
    WbdTransportLineType saveTransportLineType(WbdTransportLineType WbdTransportLineType);

    /**
     * 获取线路类型分页数据
     *
     * @param page         页码
     * @param pageSize     页尺寸
     * @param typeNumber   类型编号
     * @param name         类型名称
     * @param agencyType 机构类型
     * @return 线路类型分页数据
     */
    IPage<WbdTransportLineType> findByPage(Integer page, Integer pageSize, String typeNumber, String name, Integer agencyType);

    /**
     * 获取线路类型列表
     *
     * @param ids 线路类型id列表
     * @return 线路类型列表
     */
    List<WbdTransportLineType> findAll(List<String> ids);

    /**
     * 删除线路类型
     *
     * @param id
     */
    void disableById(String id);
}
