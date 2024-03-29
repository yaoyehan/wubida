package com.yyh.wubida.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yyh.wubida.DTO.base.GoodsTypeDto;
import com.yyh.wubida.common.utils.Constant;
import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.common.utils.Result;
import com.yyh.wubida.entity.base.WbdGoodsType;
import com.yyh.wubida.entity.truck.WbdTruckTypeGoodsType;
import com.yyh.wubida.service.base.IWbdGoodsTypeService;
import com.yyh.wubida.service.truck.IWbdTruckTypeGoodsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 货物类型管理
 */
@RestController
@RequestMapping("base/goodsType")
@Api(tags = "货物类型管理")
public class GoodsTypeController {
    @Autowired
    private IWbdGoodsTypeService goodsTypeService;

    @Autowired
    private IWbdTruckTypeGoodsTypeService truckTypeGoodsTypeService;

    /**
     * 新增货物类型，同时需要关联车辆类型
     * @param goodsTypeDto 货物类型信息
     * @return 货物类型信息
     */
    @PostMapping("")
    @ApiOperation(value = "添加货物类型")
    public GoodsTypeDto saveGoodsType(@Validated @RequestBody GoodsTypeDto goodsTypeDto){
        WbdGoodsType WbdGoodsType = new WbdGoodsType();
        BeanUtils.copyProperties(goodsTypeDto,WbdGoodsType);

        //保存货物类型信息到货物类型表
        WbdGoodsType = goodsTypeService.saveGoodsType(WbdGoodsType);
        String goodsTypeId = WbdGoodsType.getId();//货物类型id

        //保存货物类型和车辆类型关联信息到关联表
        List<String> truckTypeIds = goodsTypeDto.getTruckTypeIds();
        if(truckTypeIds != null && truckTypeIds.size() > 0){
            List<WbdTruckTypeGoodsType> list = truckTypeIds.stream().map(truckTypeId -> {
                WbdTruckTypeGoodsType WbdTruckTypeGoodsType = new WbdTruckTypeGoodsType();
                WbdTruckTypeGoodsType.setGoodsTypeId(goodsTypeId);//货物类型id
                WbdTruckTypeGoodsType.setTruckTypeId(truckTypeId);//车辆类型id
                return WbdTruckTypeGoodsType;
            }).collect(Collectors.toList());
            //批量保存货物类型和车辆类型的关联信息
            truckTypeGoodsTypeService.batchSave(list);
        }

        BeanUtils.copyProperties(WbdGoodsType,goodsTypeDto);
        return goodsTypeDto;
    }

    /**
     * 根据id查询货物类型
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询货物类型")
    public GoodsTypeDto findById(@PathVariable(name = "id") String id){
        WbdGoodsType WbdGoodsType = goodsTypeService.getById(id);

        GoodsTypeDto goodsTypeDto = new GoodsTypeDto();
        BeanUtils.copyProperties(WbdGoodsType,goodsTypeDto);

        //还需要查询当前货物类型关联的车辆类型的id
        List<WbdTruckTypeGoodsType> list = truckTypeGoodsTypeService.findAll(null, id);
        if(list != null && list.size() > 0){
            List<String> truckTypeId = list.stream().map(WbdTruckTypeGoodsType ->
                WbdTruckTypeGoodsType.getTruckTypeId()
            ).collect(Collectors.toList());
            goodsTypeDto.setTruckTypeIds(truckTypeId);
        }

        return goodsTypeDto;
    }

    /**
     * 查询所有货物类型
     * @return
     */
    @GetMapping("/all")
    @ApiOperation(value = "查询所有货物类型")
    public List<GoodsTypeDto> findAll(){
        List<WbdGoodsType> goodsTypeList = goodsTypeService.findAll();
        if(goodsTypeList != null && goodsTypeList.size() > 0){
            List<GoodsTypeDto> goodsTypeDtoList = goodsTypeList.stream().map(goodsType -> {
                GoodsTypeDto goodsTypeDto = new GoodsTypeDto();
                BeanUtils.copyProperties(goodsType, goodsTypeDto);
                return goodsTypeDto;
            }).collect(Collectors.toList());
            return goodsTypeDtoList;
        }
        return null;
    }

    /**
     * 分页查询货物类型
     * @param page
     * @param pageSize
     * @param name
     * @param truckTypeId
     * @param truckTypeName
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询货物类型")
    public PageResponse<GoodsTypeDto> findByPage(@RequestParam(name = "page") Integer page,
                                                 @RequestParam(name = "pageSize") Integer pageSize,
                                                 @RequestParam(name = "name", required = false) String name,
                                                 @RequestParam(name = "truckTypeId", required = false) String truckTypeId,
                                                 @RequestParam(name = "truckTypeName", required = false) String truckTypeName){

        //分页查询
        IPage<WbdGoodsType> goodsTypePage = goodsTypeService.findByPage(page, pageSize, name, truckTypeId, truckTypeName);
        List<WbdGoodsType> goodsTypePageRecords = goodsTypePage.getRecords();

        if(goodsTypePageRecords != null && goodsTypePageRecords.size() > 0){
            List<GoodsTypeDto> goodsTypeDtoList = goodsTypePageRecords.stream().map(goodsType -> {
                List<WbdTruckTypeGoodsType> truckTypeGoodsTypes = truckTypeGoodsTypeService.findAll(null, goodsType.getId());
                List<String> truckTypeIds = truckTypeGoodsTypes.stream().map(WbdTruckTypeGoodsType -> WbdTruckTypeGoodsType.getTruckTypeId()).collect(Collectors.toList());
                GoodsTypeDto goodsTypeDto = new GoodsTypeDto();
                BeanUtils.copyProperties(goodsType, goodsTypeDto);
                goodsTypeDto.setTruckTypeIds(truckTypeIds);
                return goodsTypeDto;
            }).collect(Collectors.toList());

            return PageResponse.<GoodsTypeDto>builder().items(goodsTypeDtoList).counts(goodsTypePage.getTotal()).page(page).pages(goodsTypePage.getPages()).pagesize(pageSize).build();
        }

        return null;
    }

    /**
     * 查询货物类型列表
     * @param ids
     * @return
     */
    @GetMapping("")
    @ApiOperation(value = "查询货物类型列表")
    public List<GoodsTypeDto> findAll(@RequestParam(name = "ids", required = false) List<String> ids){
        List<WbdGoodsType> list= goodsTypeService.findAll(ids);
        if(list != null && list.size() > 0){
            List<GoodsTypeDto> goodsTypeDtoList = list.stream().map(WbdGoodsType -> {
                List<WbdTruckTypeGoodsType> truckTypeGoodsTypes = truckTypeGoodsTypeService.findAll(null, WbdGoodsType.getId());
                List<String> truckTypeIds = truckTypeGoodsTypes.stream().map(truckTypeGoodsType -> truckTypeGoodsType.getTruckTypeId()).collect(Collectors.toList());
                GoodsTypeDto goodsTypeDto = new GoodsTypeDto();
                BeanUtils.copyProperties(WbdGoodsType, goodsTypeDto);
                goodsTypeDto.setTruckTypeIds(truckTypeIds);
                return goodsTypeDto;
            }).collect(Collectors.toList());
            return goodsTypeDtoList;
        }
        return null;
    }

    /**
     * 更新货物类型信息
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "更新货物类型信息")
    public GoodsTypeDto uWbdateById(@PathVariable(name = "id") String id, @RequestBody GoodsTypeDto dto){
        WbdGoodsType WbdGoodsType = new WbdGoodsType();
        BeanUtils.copyProperties(dto,WbdGoodsType);
        WbdGoodsType.setId(id);
        //更新货物类型的普通属性
        goodsTypeService.updateById(WbdGoodsType);

        //更新关联信息
        List<String> truckTypeIds = dto.getTruckTypeIds();
        if(truckTypeIds != null && truckTypeIds.size() > 0){
            //清理原有关联信息
            truckTypeGoodsTypeService.delete(null,id);
            //重新建立关联关系
            List<WbdTruckTypeGoodsType> list = truckTypeIds.stream().map(truckTypeId -> {
                WbdTruckTypeGoodsType WbdTruckTypeGoodsType = new WbdTruckTypeGoodsType();
                WbdTruckTypeGoodsType.setGoodsTypeId(id);//货物类型id
                WbdTruckTypeGoodsType.setTruckTypeId(truckTypeId);//车辆类型id
                return WbdTruckTypeGoodsType;
            }).collect(Collectors.toList());
            //批量保存货物类型和车辆类型的关联信息
            truckTypeGoodsTypeService.batchSave(list);
        }

        dto.setId(id);
        return dto;
    }

    /**
     * 删除货物类型--逻辑删除
     *
     * @param id 货物类型id
     * @return 返回信息
     */
    @PutMapping("/{id}/disable")
    @ApiOperation(value = "删除货物类型")
    public Result disable(@PathVariable(name = "id") String id) {
        WbdGoodsType WbdGoodsType = new WbdGoodsType();
        WbdGoodsType.setId(id);
        WbdGoodsType.setStatus(Constant.DATA_DISABLE_STATUS);
        goodsTypeService.updateById(WbdGoodsType);
        return Result.ok();
    }
}
