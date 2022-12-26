package com.yyh.wubida.service;

import com.yyh.wubida.DTO.AppDriverQueryDTO;
import com.yyh.wubida.DTO.DriverJobDTO;
import com.yyh.wubida.common.utils.PageResponse;

public interface DriverService {
    PageResponse<DriverJobDTO> findByPage(AppDriverQueryDTO dto);
}
