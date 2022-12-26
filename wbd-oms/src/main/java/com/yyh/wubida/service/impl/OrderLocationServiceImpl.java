package com.yyh.wubida.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.entity.OrderLocation;
import com.yyh.wubida.mapper.OrderLocationMapper;
import com.yyh.wubida.service.IOrderLocationService;
import org.springframework.stereotype.Service;

/**
 * 位置信息服务实现
 */
@Service
public class OrderLocationServiceImpl extends ServiceImpl<OrderLocationMapper, OrderLocation> implements IOrderLocationService {

}