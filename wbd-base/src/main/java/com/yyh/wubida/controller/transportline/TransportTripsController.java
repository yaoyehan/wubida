package com.yyh.wubida.controller.transportline;

import java.util.List;
import java.util.stream.Collectors;

import com.yyh.wubida.common.utils.Result;
import com.yyh.wubida.DTO.transportline.TransportTripsTruckDriverDto;
import com.yyh.wubida.entity.transportline.WbdTransportTrips;
import com.yyh.wubida.entity.transportline.WbdTransportTripsTruckDriver;
import com.yyh.wubida.service.transportline.IWbdTransportTripsService;
import com.yyh.wubida.DTO.transportline.TransportTripsDto;

import com.yyh.wubida.service.transportline.IWbdTransportTripsTruckDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.BeanUtils;

/**
 * TransportTripsController
 */
@RestController
@RequestMapping("base/transportLine/trips")
public class TransportTripsController {
    @Autowired
    private IWbdTransportTripsService transportTripsService;
    @Autowired
    private IWbdTransportTripsTruckDriverService transportTripsTruckDriverService;

    /**
     * 添加车次
     *
     * @param dto 车次信息
     * @return 车次信息
     */
    @PostMapping("")
    public TransportTripsDto save(@RequestBody TransportTripsDto dto) {
        WbdTransportTrips WbdTransportTrips = new WbdTransportTrips();
        BeanUtils.copyProperties(dto, WbdTransportTrips);
        WbdTransportTrips = transportTripsService.saveTransportTrips(WbdTransportTrips);
        BeanUtils.copyProperties(WbdTransportTrips, dto);
        return dto;
    }

    /**
     * 根据id获取车次详情
     *
     * @param id 车次id
     * @return 车次信息
     */
    @GetMapping("/{id}")
    public TransportTripsDto fineById(@PathVariable(name = "id") String id) {
        WbdTransportTrips WbdTransportTrips = transportTripsService.getById(id);
        TransportTripsDto dto = new TransportTripsDto();
        if (WbdTransportTrips != null) {
            BeanUtils.copyProperties(WbdTransportTrips, dto);
        }else{
            dto.setId(id);
        }
        return dto;
    }

    /**
     * 获取车次列表
     *
     * @param transportLineId 线路id
     * @param ids             车次id列表
     * @return 车次列表
     */
    @GetMapping("")
    public List<TransportTripsDto> findAll(@RequestParam(name = "transportLineId", required = false) String transportLineId, @RequestParam(name = "ids", required = false) List<String> ids) {
        return transportTripsService.findAll(transportLineId, ids).stream().map(WbdTransportTrips -> {
            TransportTripsDto dto = new TransportTripsDto();
            BeanUtils.copyProperties(WbdTransportTrips, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * 更新车次信息
     *
     * @param id  车次id
     * @param dto 车次信息
     * @return 车次信息
     */
    @PutMapping("/{id}")
    public TransportTripsDto uWbdate(@PathVariable(name = "id") String id, @RequestBody TransportTripsDto dto) {
        dto.setId(id);
        WbdTransportTrips WbdTransportTrips = new WbdTransportTrips();
        BeanUtils.copyProperties(dto, WbdTransportTrips);
        transportTripsService.updateById(WbdTransportTrips);
        return dto;
    }

    /**
     * 删除车次信息
     *
     * @param id 车次信息
     * @return 返回信息
     */
    @PutMapping("/{id}/disable")
    public Result disable(@PathVariable(name = "id") String id) {
        transportTripsService.disable(id);
        return Result.ok();
    }

    /**
     * 批量保存车次与车辆和司机关联关系
     *
     * @param dtoList 车次与车辆和司机关联关系
     * @return 返回信息
     */
    @PostMapping("{id}/truckDriver")
    public Result batchSaveTruckDriver(@PathVariable("id") String transportTripsId, @RequestBody List<TransportTripsTruckDriverDto> dtoList) {
        transportTripsTruckDriverService.batchSave(transportTripsId, dtoList.stream().map(dto -> {
            dto.setTransportTripsId(transportTripsId);
            WbdTransportTripsTruckDriver truckTransportTripsTruckDriver = new WbdTransportTripsTruckDriver();
            BeanUtils.copyProperties(dto, truckTransportTripsTruckDriver);
            return truckTransportTripsTruckDriver;
        }).collect(Collectors.toList()));
        return Result.ok();
    }

    /**
     * 获取车次与车辆和司机关联关系列表
     *
     * @param transportTripsId 车次id
     * @param truckId          车辆id
     * @param userId           司机id
     * @return 车次与车辆和司机关联关系列表
     */
    @GetMapping("/truckDriver")
    public List<TransportTripsTruckDriverDto> findAllTruckDriverTransportTrips(@RequestParam(name = "transportTripsId", required = false) String transportTripsId, @RequestParam(name = "truckId", required = false) String truckId, @RequestParam(name = "userId", required = false) String userId) {
        return transportTripsTruckDriverService.findAll(transportTripsId, truckId, userId).stream().map(WbdTransportTripsTruck -> {
            TransportTripsTruckDriverDto dto = new TransportTripsTruckDriverDto();
            BeanUtils.copyProperties(WbdTransportTripsTruck, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}