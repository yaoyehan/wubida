package com.yyh.wubida.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yyh.wubida.DTO.AppCourierQueryDTO;
import com.yyh.wubida.DTO.TaskPickupDispatchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CourierMapper extends BaseMapper {
    IPage<TaskPickupDispatchDTO> findByPage(IPage<TaskPickupDispatchDTO> iPage, @Param("params") AppCourierQueryDTO dto);
}
