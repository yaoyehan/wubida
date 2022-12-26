package com.yyh.wubida.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyh.wubida.entity.OrderClassifyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OrderClassifyMapper extends BaseMapper<OrderClassifyEntity> {
}
