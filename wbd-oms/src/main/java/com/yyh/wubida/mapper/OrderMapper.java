package com.yyh.wubida.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyh.wubida.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 订单 Mapper 接口
 */

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
