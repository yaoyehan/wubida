package com.yyh.wubida.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.entity.base.WbdGoodsType;
import com.yyh.wubida.mapper.base.WbdGoodsTypeMapper;
import com.yyh.wubida.service.base.IWbdGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 货物类型管理服务接口实现
 */
@Service
public class WbdGoodsTypeServiceImpl extends ServiceImpl<WbdGoodsTypeMapper,WbdGoodsType> implements IWbdGoodsTypeService {
    @Autowired
    private CustomIdGenerator idGenerator;
    /**
     * 保存货物类型
     * @param WbdGoodsType
     * @return
     */
    @Override
    public WbdGoodsType saveGoodsType(WbdGoodsType WbdGoodsType) {
        WbdGoodsType.setId(idGenerator.nextId(WbdGoodsType) + "");
        baseMapper.insert(WbdGoodsType);
        return WbdGoodsType;
    }

    /**
     * 查询所有货物类型
     * @return
     */
    @Override
    public List<WbdGoodsType> findAll() {
        QueryWrapper<WbdGoodsType> queryWrapper = new QueryWrapper<>();
        //添加条件，根据status查询
        queryWrapper.eq("status",1);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询货物类型
     * @param page
     * @param pageSize
     * @param name
     * @param truckTypeId
     * @param truckTypeName
     * @return
     */
    @Override
    public IPage<WbdGoodsType> findByPage(Integer page, Integer pageSize, String name, String truckTypeId, String truckTypeName) {
        //封装分页条件
        Page<WbdGoodsType> WbdPage = new Page<>(page,pageSize);
        //排序条件
        WbdPage.addOrder(OrderItem.asc("id"));
        WbdPage.setRecords(baseMapper.findByPage(WbdPage,name,truckTypeId,truckTypeName));
        return WbdPage;
    }

    /**
     * 查询货物类型列表
     * @param ids
     * @return
     */
    @Override
    public List<WbdGoodsType> findAll(List<String> ids) {
        LambdaQueryWrapper<WbdGoodsType> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if(ids != null && ids.size() > 0){
            //添加查询条件
            lambdaQueryWrapper.in(WbdGoodsType::getId,ids);
        }
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
