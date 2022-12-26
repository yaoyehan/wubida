package com.yyh.wubida.controller;

import com.yyh.wubida.DTO.DriverJobDTO;
import com.yyh.wubida.DTO.webManager.DriverJobQueryDTO;
import com.itheima.pinda.authority.api.AreaApi;
import com.itheima.pinda.authority.api.OrgApi;
import com.itheima.pinda.authority.api.UserApi;
import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.feign.OrderFeign;
import com.yyh.wubida.feign.TransportOrderFeign;
import com.yyh.wubida.feign.TransportTaskFeign;
import com.yyh.wubida.feign.transportline.TransportTripsFeign;
import com.yyh.wubida.feign.truck.TruckFeign;
import com.yyh.wubida.feign.webManager.WebManagerFeign;
import com.yyh.wubida.util.BeanUtil;
import com.yyh.wubida.vo.work.DriverJobVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 司机作业单相关API
 *
 * @author itcast
 */
@RestController
@Slf4j
@Api(tags = "司机作业单相关API")
@RequestMapping("driver-job-manager")
public class DriverJobController {
    @Autowired
    private WebManagerFeign webManagerFeign;
    @Autowired
    private TransportTaskFeign transportTaskFeign;
    @Autowired
    private TransportTripsFeign transportTripsFeign;
    @Autowired
    private OrgApi orgApi;
    @Autowired
    private UserApi userApi;
    @Autowired
    private TruckFeign truckFeign;
    @Autowired
    private TransportOrderFeign transportOrderFeign;
    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private AreaApi areaApi;

    @ApiOperation(value = "获取司机作业单分页数据")
    @PostMapping("/page")
    public PageResponse<DriverJobVo> findByPage(@RequestBody DriverJobVo vo) {
        DriverJobQueryDTO dto = new DriverJobQueryDTO();
        if (vo != null) {
            dto.setPage(vo.getPage());
            dto.setPageSize(vo.getPageSize());
            if (vo.getDriver() != null) {
                dto.setDriverName(vo.getDriver().getName());
            }
            if (vo.getTaskTransport() != null) {
                dto.setTaskTransportId(vo.getTaskTransport().getId());
            }
            dto.setStatus(vo.getStatus());
            dto.setId(vo.getId());
        }
        PageResponse<DriverJobDTO> dtoPageResponse = webManagerFeign.findDriverJobByPage(dto);
        List<DriverJobDTO> dtoList = dtoPageResponse.getItems();
        List<DriverJobVo> voList = dtoList.stream().map(driverJobDTO -> BeanUtil.parseDriverJobDTO2Vo(driverJobDTO, transportTripsFeign, orgApi, userApi, truckFeign, transportOrderFeign, orderFeign, areaApi, transportTaskFeign)).collect(Collectors.toList());
        return PageResponse.<DriverJobVo>builder().items(voList).pagesize(vo.getPageSize()).page(vo.getPage()).counts(dtoPageResponse.getCounts()).pages(dtoPageResponse.getPages()).build();
    }
}
