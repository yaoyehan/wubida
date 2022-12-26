package com.yyh.wubida.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.OrderClassifyOrderEntity;

/**
 * 订单分类关联订单
 */
public interface IOrderClassifyOrderService extends IService<OrderClassifyOrderEntity> {
    String getClassifyId(String orderId);
}