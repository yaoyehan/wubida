package com.yyh.wubida.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyh.wubida.entity.Order;
import com.yyh.wubida.entity.Rule;
import org.apache.ibatis.annotations.Mapper;


/**
 * 规则
 */

@Mapper
public interface RuleMapper extends BaseMapper<Rule> {
}