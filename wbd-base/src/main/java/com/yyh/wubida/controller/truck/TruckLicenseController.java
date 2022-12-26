package com.yyh.wubida.controller.truck;

import com.yyh.wubida.entity.truck.WbdTruck;
import com.yyh.wubida.entity.truck.WbdTruckLicense;
import com.yyh.wubida.service.truck.IWbdTruckLicenseService;
import com.yyh.wubida.DTO.truck.TruckLicenseDto;

import com.yyh.wubida.service.truck.IWbdTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.BeanUtils;

/**
 * TruckLicenseController
 */
@RestController
@RequestMapping("base/truck/license")
public class TruckLicenseController {
    @Autowired
    private IWbdTruckLicenseService truckLicenseService;
    @Autowired
    private IWbdTruckService truckService;

    /**
     * 保存车辆行驶证信息
     *
     * @param dto 车辆行驶证信息
     * @return 车辆行驶证信息
     */
    @PostMapping("")
    public TruckLicenseDto saveTruckLicense(@RequestBody TruckLicenseDto dto) {
        WbdTruckLicense WbdTruckLicense = new WbdTruckLicense();
        BeanUtils.copyProperties(dto, WbdTruckLicense);
        WbdTruckLicense = truckLicenseService.saveTruckLicense(WbdTruckLicense);
        if (dto.getId() == null) {
            WbdTruck truck = new WbdTruck();
            truck.setId(dto.getId());
            truck.setTruckLicenseId(WbdTruckLicense.getId());
            truckService.saveTruck(truck);
        }
        BeanUtils.copyProperties(WbdTruckLicense, dto);
        return dto;
    }

    /**
     * 根据id获取车辆行驶证详情
     *
     * @param id 车辆行驶证id
     * @return 车辆行驶证信息
     */
    @GetMapping("/{id}")
    public TruckLicenseDto fineById(@PathVariable(name = "id") String id) {
        WbdTruckLicense WbdTruckLicense = truckLicenseService.getById(id);
        TruckLicenseDto dto = new TruckLicenseDto();
        BeanUtils.copyProperties(WbdTruckLicense, dto);
        return dto;
    }
}