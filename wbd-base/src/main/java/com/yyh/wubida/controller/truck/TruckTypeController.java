package com.yyh.wubida.controller.truck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yyh.wubida.common.utils.Constant;
import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.common.utils.Result;
import com.yyh.wubida.entity.truck.WbdTruckType;
import com.yyh.wubida.entity.truck.WbdTruckTypeGoodsType;
import com.yyh.wubida.service.truck.IWbdTruckTypeGoodsTypeService;
import com.yyh.wubida.service.truck.IWbdTruckTypeService;
import com.yyh.wubida.DTO.truck.TruckTypeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.BeanUtils;

/**
 * TruckTypeController
 */
@RestController
@RequestMapping("base/truck/type")
public class TruckTypeController {
    @Autowired
    private IWbdTruckTypeService truckTypeService;
    @Autowired
    private IWbdTruckTypeGoodsTypeService truckTypeGoodsTypeService;

    /**
     * 添加车辆类型
     *
     * @param dto 车辆类型信息
     * @return 车辆类型信息
     */
    @PostMapping("")
    public TruckTypeDto saveTruckType(@RequestBody TruckTypeDto dto) {
        WbdTruckType WbdTruckType = new WbdTruckType();
        BeanUtils.copyProperties(dto, WbdTruckType);
        WbdTruckType = truckTypeService.saveTruckType(WbdTruckType);
        String truckTypeId = WbdTruckType.getId();
        //处理与货物类型的关联
        if (dto.getGoodsTypeIds() != null) {
            truckTypeGoodsTypeService.batchSave(dto.getGoodsTypeIds().stream().map(id -> {
                WbdTruckTypeGoodsType truckTypeGoodsType = new WbdTruckTypeGoodsType();
                truckTypeGoodsType.setGoodsTypeId(id);
                truckTypeGoodsType.setTruckTypeId(truckTypeId);
                return truckTypeGoodsType;
            }).collect(Collectors.toList()));
        }
        BeanUtils.copyProperties(WbdTruckType, dto);
        return dto;
    }

    /**
     * 根据id获取车辆类型详情
     *
     * @param id 车辆类型id
     * @return 车辆类型信息
     */
    @GetMapping("/{id}")
    public TruckTypeDto fineById(@PathVariable(name = "id") String id) {
        WbdTruckType WbdTruckType = truckTypeService.getById(id);
        TruckTypeDto dto = new TruckTypeDto();
        BeanUtils.copyProperties(WbdTruckType, dto);
        dto.setGoodsTypeIds(truckTypeGoodsTypeService.findAll(dto.getId(), null).stream().map(WbdTruckTypeGoodsType -> WbdTruckTypeGoodsType.getGoodsTypeId()).collect(Collectors.toList()));
        return dto;
    }

    /**
     * 获取车辆类型分页数据
     *
     * @param page            页码
     * @param pageSize        页尺寸
     * @param name            车辆类型名称
     * @param allowableLoad   车辆载重
     * @param allowableVolume 车辆体积
     * @return 车辆类型分页数据
     */
    @GetMapping("/page")
    public PageResponse<TruckTypeDto> findByPage(@RequestParam(name = "page") Integer page,
                                                 @RequestParam(name = "pageSize") Integer pageSize,
                                                 @RequestParam(name = "name", required = false) String name,
                                                 @RequestParam(name = "allowableLoad", required = false) BigDecimal allowableLoad,
                                                 @RequestParam(name = "allowableVolume", required = false) BigDecimal allowableVolume) {
        IPage<WbdTruckType> WbdTruckTypePage = truckTypeService.findByPage(page, pageSize, name, allowableLoad,
                allowableVolume);
        List<TruckTypeDto> dtoList = new ArrayList<>();
        WbdTruckTypePage.getRecords().forEach(WbdTruckType -> {
            TruckTypeDto dto = new TruckTypeDto();
            BeanUtils.copyProperties(WbdTruckType, dto);
            dto.setGoodsTypeIds(truckTypeGoodsTypeService.findAll(dto.getId(), null).stream().map(WbdTruckTypeGoodsType -> WbdTruckTypeGoodsType.getGoodsTypeId()).collect(Collectors.toList()));
            dtoList.add(dto);
        });
        return PageResponse.<TruckTypeDto>builder().items(dtoList).pagesize(pageSize).page(page)
                .counts(WbdTruckTypePage.getTotal()).pages(WbdTruckTypePage.getPages()).build();
    }

    /**
     * 获取车辆类型列表
     *
     * @param ids 车辆类型id
     * @return 车辆类型列表
     */
    @GetMapping("")
    public List<TruckTypeDto> findAll(@RequestParam(name = "ids",required = false) List<String> ids) {
        return truckTypeService.findAll(ids).stream().map(truckType -> {
            TruckTypeDto dto = new TruckTypeDto();
            BeanUtils.copyProperties(truckType, dto);
            dto.setGoodsTypeIds(truckTypeGoodsTypeService.findAll(dto.getId(), null).stream().map(WbdTruckTypeGoodsType -> WbdTruckTypeGoodsType.getGoodsTypeId()).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * 更新车辆类型信息
     *
     * @param id  车辆类型id
     * @param dto 车辆类型信息
     * @return 车辆类型信息
     */
    @PutMapping("/{id}")
    public TruckTypeDto uWbdate(@PathVariable(name = "id") String id, @RequestBody TruckTypeDto dto) {
        dto.setId(id);
        WbdTruckType truckType = new WbdTruckType();
        BeanUtils.copyProperties(dto, truckType);
        truckTypeService.updateById(truckType);
        //处理与货物类型的关联
        if (dto.getGoodsTypeIds() != null) {
            truckTypeGoodsTypeService.delete(id, null);
            //绑定新的关系
            truckTypeGoodsTypeService.batchSave(dto.getGoodsTypeIds().stream().map(goodsTypeId -> {
                WbdTruckTypeGoodsType truckTypeGoodsType = new WbdTruckTypeGoodsType();
                truckTypeGoodsType.setGoodsTypeId(goodsTypeId);
                truckTypeGoodsType.setTruckTypeId(id);
                return truckTypeGoodsType;
            }).collect(Collectors.toList()));
        }
        return dto;
    }

    /**
     * 删除车辆类型
     *
     * @param id 车辆类型Id
     * @return 返回信息
     */
    @PutMapping("/{id}/disable")
    public Result disable(@PathVariable(name = "id") String id) {
        // TODO: 2020/1/8 待实现，是否关联数据
        WbdTruckType truckType = new WbdTruckType();
        truckType.setId(id);
        truckType.setStatus(Constant.DATA_DISABLE_STATUS);
        truckTypeService.updateById(truckType);
        return Result.ok();
    }

}