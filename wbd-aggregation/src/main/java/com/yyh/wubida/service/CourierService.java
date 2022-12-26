package com.yyh.wubida.service;

import com.yyh.wubida.DTO.AppCourierQueryDTO;
import com.yyh.wubida.DTO.TaskPickupDispatchDTO;
import com.yyh.wubida.common.utils.PageResponse;

public interface CourierService {
    PageResponse<TaskPickupDispatchDTO> findByPage(AppCourierQueryDTO dto);
}
