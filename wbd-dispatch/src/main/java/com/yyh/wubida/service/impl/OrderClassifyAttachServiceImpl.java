/**
 * Copyright (c) 2019 联智合创 All rights reserved.
 * <p>
 * http://www.witlinked.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.yyh.wubida.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.entity.OrderClassifyAttachEntity;
import com.yyh.wubida.mapper.OrderClassifyAttachMapper;
import com.yyh.wubida.service.IOrderClassifyAttachService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderClassifyAttachServiceImpl extends ServiceImpl<OrderClassifyAttachMapper, OrderClassifyAttachEntity> implements IOrderClassifyAttachService {

    @Override
    public List<OrderClassifyAttachEntity> findAttachByClassifyId(String classifyId) {

        LambdaQueryWrapper<OrderClassifyAttachEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderClassifyAttachEntity::getOrderClassifyId, classifyId);

        // 正序排列  从先到后
        wrapper.orderByAsc(OrderClassifyAttachEntity::getCreateDate);
        return this.baseMapper.selectList(wrapper);
    }
}