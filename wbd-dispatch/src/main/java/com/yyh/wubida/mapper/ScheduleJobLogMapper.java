package com.yyh.wubida.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyh.wubida.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLogEntity> {

}
