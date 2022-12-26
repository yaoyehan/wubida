package com.yyh.wubida.service;

import com.yyh.wubida.DTO.OrderClassifyGroupDTO;
import com.yyh.wubida.DTO.OrderLineSimpleDTO;

import java.util.List;

/**
 * 路线规划
 */
public interface ITaskRoutePlanningService {
    /**
     * 执行
     * @return
     */
    List<OrderLineSimpleDTO> execute(List<OrderClassifyGroupDTO> orderClassifyGroupDTOS, String agencyId, String jobId, String logId, String params);
}
