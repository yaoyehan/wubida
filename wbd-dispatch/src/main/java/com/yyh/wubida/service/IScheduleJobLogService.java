package com.yyh.wubida.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.DTO.ScheduleJobLogDTO;
import com.yyh.wubida.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 */
public interface IScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    IPage<ScheduleJobLogEntity> page(Map<String, Object> params);

    ScheduleJobLogDTO get(Long id);
}
