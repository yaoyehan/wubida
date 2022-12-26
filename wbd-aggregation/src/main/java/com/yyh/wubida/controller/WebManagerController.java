package com.yyh.wubida.controller;

import com.yyh.wubida.DTO.DriverJobDTO;
import com.yyh.wubida.DTO.TaskPickupDispatchDTO;
import com.yyh.wubida.DTO.TaskTransportDTO;
import com.yyh.wubida.DTO.TransportOrderDTO;
import com.yyh.wubida.DTO.webManager.DriverJobQueryDTO;
import com.yyh.wubida.DTO.webManager.TaskPickupDispatchQueryDTO;
import com.yyh.wubida.DTO.webManager.TaskTransportQueryDTO;
import com.yyh.wubida.DTO.webManager.TransportOrderQueryDTO;
import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.service.WebManagerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "管理后台聚合平台")
@RestController
@RequestMapping("webManager")
public class WebManagerController {
    @Autowired
    private WebManagerService webManagerService;

    @PostMapping("driverJob/page")
    PageResponse<DriverJobDTO> findDriverJobByPage(@RequestBody DriverJobQueryDTO dto) {
        return webManagerService.findDriverJobByPage(dto);
    }

    @PostMapping("taskPickupDispatchJob/page")
    PageResponse<TaskPickupDispatchDTO> findTaskPickupDispatchJobByPage(@RequestBody TaskPickupDispatchQueryDTO dto) {
        return webManagerService.findTaskPickupDispatchJobByPage(dto);
    }

    @PostMapping("transportOrder/page")
    PageResponse<TransportOrderDTO> findTransportOrderByPage(@RequestBody TransportOrderQueryDTO dto) {
        return webManagerService.findTransportOrderByPage(dto);
    }

    @PostMapping("taskTransport/page")
    PageResponse<TaskTransportDTO> findTransportOrderByPage(@RequestBody TaskTransportQueryDTO dto) {
        return webManagerService.findTaskTransportByPage(dto);
    }
}
