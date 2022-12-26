package com.yyh.wubida.controller.truck;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yyh.wubida.DTO.truck.TruckDto;
import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.common.utils.Result;
import com.yyh.wubida.entity.truck.WbdTruck;
import com.yyh.wubida.service.truck.IWbdTruckService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TruckController
 */
@RestController
@RequestMapping("base/truck")
public class TruckController {
    @Autowired
    private IWbdTruckService truckService;

    /**
     * 添加车辆
     *
     * @param dto 车辆信息
     * @return 车辆信息
     */
    @PostMapping("")
    public TruckDto saveTruck(@RequestBody TruckDto dto) {
        WbdTruck WbdTruck = new WbdTruck();
        BeanUtils.copyProperties(dto, WbdTruck);
        WbdTruck = truckService.saveTruck(WbdTruck);
        BeanUtils.copyProperties(WbdTruck, dto);
        return dto;
    }

    /**
     * 根据id获取车辆详情
     *
     * @param id 车辆id
     * @return 车辆信息
     */
    @GetMapping("/{id}")
    public TruckDto fineById(@PathVariable(name = "id") String id) {
        WbdTruck WbdTruck = truckService.getById(id);
        if (ObjectUtils.isEmpty(WbdTruck)) {
            return null;
        }
        TruckDto dto = new TruckDto();
        BeanUtils.copyProperties(WbdTruck, dto);
        return dto;
    }

    /**
     * 获取车辆分页数据
     *
     * @param page         页码
     * @param pageSize     页尺寸
     * @param truckTypeId  车辆类型id
     * @param licensePlate 车牌号码
     * @return 车辆分页数据
     */
    @GetMapping("/page")
    public PageResponse<TruckDto> findByPage(@RequestParam(name = "page") Integer page,
                                             @RequestParam(name = "pageSize") Integer pageSize,
                                             @RequestParam(name = "truckTypeId", required = false) String truckTypeId,
                                             @RequestParam(name = "licensePlate", required = false) String licensePlate,
                                             @RequestParam(name = "fleetId", required = false) String fleetId) {
        // TODO: 2020/1/9 通过车队名称查询待实现
        IPage<WbdTruck> truckPage = truckService.findByPage(page, pageSize, truckTypeId, licensePlate, fleetId);
        List<TruckDto> dtoList = new ArrayList<>();
        truckPage.getRecords().forEach(WbdTruck -> {
            TruckDto dto = new TruckDto();
            BeanUtils.copyProperties(WbdTruck, dto);
            dtoList.add(dto);
        });
        return PageResponse.<TruckDto>builder().items(dtoList).pagesize(pageSize).page(page).counts(truckPage.getTotal())
                .pages(truckPage.getPages()).build();
    }

    /**
     * 统计车辆数量
     *
     * @param fleetId 车队id
     * @return 车辆数量
     */
    @GetMapping("/count")
    public Integer count(@RequestParam(name = "fleetId", required = false) String fleetId) {
        return truckService.count(fleetId);
    }

    /**
     * 获取车辆列表
     *
     * @param ids 车辆id列表
     * @return 车辆列表
     */
    @GetMapping("")
    public List<TruckDto> findAll(@RequestParam(name = "ids", required = false) List<String> ids, @RequestParam(name = "fleetId", required = false) String fleetId) {
        return truckService.findAll(ids, fleetId).stream().map(WbdTruck -> {
            TruckDto dto = new TruckDto();
            BeanUtils.copyProperties(WbdTruck, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * 更新车辆信息
     *
     * @param id  车辆id
     * @param dto 车辆信息
     * @return 车辆信息
     */
    @PutMapping("/{id}")
    public TruckDto uWbdate(@PathVariable(name = "id") String id, @RequestBody TruckDto dto) {
        dto.setId(id);
        WbdTruck WbdTruck = new WbdTruck();
        BeanUtils.copyProperties(dto, WbdTruck);
        truckService.updateById(WbdTruck);
        return dto;
    }

    /**
     * 删除车辆
     *
     * @param id 车辆id
     * @return 返回信息
     */
    @PutMapping("/{id}/disable")
    public Result disable(@PathVariable(name = "id") String id) {
        //TODO 检查车辆当前状态，如处于非空闲状态，则不允许删除
        truckService.disableById(id);
        return Result.ok();
    }
}