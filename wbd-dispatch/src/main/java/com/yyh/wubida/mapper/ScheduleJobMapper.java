package com.yyh.wubida.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyh.wubida.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 定时任务  Mapper 接口
 */
@Component
@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {

}
