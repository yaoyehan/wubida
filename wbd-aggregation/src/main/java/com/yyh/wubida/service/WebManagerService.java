package com.yyh.wubida.service;

import com.yyh.wubida.DTO.DriverJobDTO;
import com.yyh.wubida.DTO.TaskPickupDispatchDTO;
import com.yyh.wubida.DTO.TaskTransportDTO;
import com.yyh.wubida.DTO.TransportOrderDTO;
import com.yyh.wubida.DTO.webManager.DriverJobQueryDTO;
import com.yyh.wubida.DTO.webManager.TaskPickupDispatchQueryDTO;
import com.yyh.wubida.DTO.webManager.TaskTransportQueryDTO;
import com.yyh.wubida.DTO.webManager.TransportOrderQueryDTO;
import com.yyh.wubida.common.utils.PageResponse;

public interface WebManagerService {
    /**
     * 获取司机作业单分页数据
     *
     * @param dto 查询参数
     * @return 司机作业单分页数据
     */
    PageResponse<DriverJobDTO> findDriverJobByPage(DriverJobQueryDTO dto);

    /**
     * 获取取派件任务分页数据
     *
     * @param dto 查询参数
     * @return 取派件分页数据
     */
    PageResponse<TaskPickupDispatchDTO> findTaskPickupDispatchJobByPage(TaskPickupDispatchQueryDTO dto);

    /**
     * 获取运单分页数据
     *
     * @param dto 查询参数
     * @return 运单分页数据
     */
    PageResponse<TransportOrderDTO> findTransportOrderByPage(TransportOrderQueryDTO dto);

    /**
     * 获取运输任务分页数据
     *
     * @param dto 查询参数
     * @return 运输任务分页数据
     */
    PageResponse<TaskTransportDTO> findTaskTransportByPage(TaskTransportQueryDTO dto);
}
