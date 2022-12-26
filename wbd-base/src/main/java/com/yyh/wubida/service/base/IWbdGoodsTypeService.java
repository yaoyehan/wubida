package com.yyh.wubida.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.base.WbdGoodsType;

import java.util.List;

/**
 * 货物类型操作接口
 */
public interface IWbdGoodsTypeService extends IService<WbdGoodsType>{
    /**
     * 保存货物类型
     * @param WbdGoodsType
     * @return
     */
    public WbdGoodsType saveGoodsType(WbdGoodsType WbdGoodsType);

    /**
     * 查询所有货物类型
     * @return
     */
    public List<WbdGoodsType> findAll();

    /**
     * 分页查询货物类型
     * @param page
     * @param pageSize
     * @param name
     * @param truckTypeId
     * @param truckTypeName
     * @return
     */
    public IPage<WbdGoodsType> findByPage(Integer page, Integer pageSize,String name,String truckTypeId,String truckTypeName);

    /**
     * 查询货物类型列表
     * @param ids
     * @return
     */
    public List<WbdGoodsType> findAll(List<String> ids);
}
