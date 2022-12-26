package com.yyh.wubida.feign.webManager;

import com.yyh.wubida.DTO.DriverJobDTO;
import com.yyh.wubida.DTO.TaskPickupDispatchDTO;
import com.yyh.wubida.DTO.TaskTransportDTO;
import com.yyh.wubida.DTO.TransportOrderDTO;
import com.yyh.wubida.DTO.webManager.DriverJobQueryDTO;
import com.yyh.wubida.DTO.webManager.TaskPickupDispatchQueryDTO;
import com.yyh.wubida.DTO.webManager.TaskTransportQueryDTO;
import com.yyh.wubida.DTO.webManager.TransportOrderQueryDTO;
import com.yyh.wubida.common.utils.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@FeignClient(name = "wbd-aggregation")
@RequestMapping("webManager")
@ApiIgnore
public interface WebManagerFeign {
    /**
     * 分页查询司机任务
     *
     * @param dto 查询参数
     * @return 司机任务分页数据
     */
    @PostMapping("driverJob/page")
    PageResponse<DriverJobDTO> findDriverJobByPage(@RequestBody DriverJobQueryDTO dto);

    /**
     * 分页查询取派件任务
     *
     * @param dto 查询参数
     * @return 取派件任务分页数据
     */
    @PostMapping("taskPickupDispatchJob/page")
    PageResponse<TaskPickupDispatchDTO> findTaskPickupDispatchJobByPage(@RequestBody TaskPickupDispatchQueryDTO dto);

    /**
     * 分页查询运单信息
     *
     * @param dto 查询参数
     * @return 运单分页数据
     */
    @PostMapping("transportOrder/page")
    PageResponse<TransportOrderDTO> findTransportOrderByPage(@RequestBody TransportOrderQueryDTO dto);

    /**
     * 分页查询运输任务信息
     *
     * @param dto 查询参数
     * @return 运输任务分页数据
     */
    @PostMapping("taskTransport/page")
    PageResponse<TaskTransportDTO> findTaskTransportByPage(@RequestBody TaskTransportQueryDTO dto);
}
