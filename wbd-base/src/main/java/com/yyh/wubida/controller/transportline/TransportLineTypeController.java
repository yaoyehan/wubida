package com.yyh.wubida.controller.transportline;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.common.utils.Result;
import com.yyh.wubida.entity.transportline.WbdTransportLineType;
import com.yyh.wubida.service.transportline.IWbdTransportLineTypeService;
import com.yyh.wubida.DTO.transportline.TransportLineTypeDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TransportLineTypeController
 */
@RestController
@RequestMapping("base/transportLine/type")
public class TransportLineTypeController {
    @Autowired
    private IWbdTransportLineTypeService transportLineTypeService;

    /**
     * 添加线路类型
     *
     * @param dto 线路类型信息
     * @return 线路类型信息
     */
    @PostMapping("")
    public TransportLineTypeDto saveTransportLineType(@RequestBody TransportLineTypeDto dto) {
        WbdTransportLineType WbdTransportLineType = new WbdTransportLineType();
        BeanUtils.copyProperties(dto, WbdTransportLineType);
        WbdTransportLineType = transportLineTypeService.saveTransportLineType(WbdTransportLineType);
        BeanUtils.copyProperties(WbdTransportLineType, dto);
        return dto;
    }

    /**
     * 根据id获取线路类型详情
     *
     * @param id 线路类型id
     * @return 线路类型详情
     */
    @GetMapping("/{id}")
    public TransportLineTypeDto fineById(@PathVariable(name = "id") String id) {
        WbdTransportLineType WbdTransportLineType = transportLineTypeService.getById(id);
        TransportLineTypeDto dto = new TransportLineTypeDto();
        BeanUtils.copyProperties(WbdTransportLineType, dto);
        return dto;
    }

    /**
     * 获取线路类型分页数据
     *
     * @param page       页码
     * @param pageSize   页尺寸
     * @param typeNumber 类型编号
     * @param name       类型名称
     * @param agencyType 机构类型
     * @return 线路类型分页数据
     */
    @GetMapping("/page")
    public PageResponse<TransportLineTypeDto> findByPage(@RequestParam(name = "page") Integer page,
                                                         @RequestParam(name = "pageSize") Integer pageSize,
                                                         @RequestParam(name = "typeNumber", required = false) String typeNumber,
                                                         @RequestParam(name = "name", required = false) String name,
                                                         @RequestParam(name = "agencyType", required = false) Integer agencyType) {
        IPage<WbdTransportLineType> transportLineTypePage = transportLineTypeService.findByPage(page, pageSize, typeNumber, name, agencyType);
        List<TransportLineTypeDto> dtoList = new ArrayList<>();
        transportLineTypePage.getRecords().forEach(WbdTransportLineType -> {
            TransportLineTypeDto dto = new TransportLineTypeDto();
            BeanUtils.copyProperties(WbdTransportLineType, dto);
            dtoList.add(dto);
        });
        return PageResponse.<TransportLineTypeDto>builder().items(dtoList).pagesize(pageSize).page(page)
                .counts(transportLineTypePage.getTotal()).pages(transportLineTypePage.getPages()).build();
    }

    /**
     * 获取线路类型列表
     *
     * @param ids 线路类型id列表
     * @return 线路类型列表
     */
    @GetMapping("")
    public List<TransportLineTypeDto> findAll(@RequestParam(name = "ids", required = false) List<String> ids) {
        return transportLineTypeService.findAll(ids).stream().map(WbdTransportLineType -> {
            TransportLineTypeDto dto = new TransportLineTypeDto();
            BeanUtils.copyProperties(WbdTransportLineType, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * 更新线路类型信息
     *
     * @param id  线路类型id
     * @param dto 线路类型信息
     * @return 线路类型信息
     */
    @PutMapping("/{id}")
    public TransportLineTypeDto uWbdate(@PathVariable(name = "id") String id, @RequestBody TransportLineTypeDto dto) {
        dto.setId(id);
        WbdTransportLineType WbdTransportLineType = new WbdTransportLineType();
        BeanUtils.copyProperties(dto, WbdTransportLineType);
        WbdTransportLineType.setLastUpdateTime(LocalDateTime.now());
        transportLineTypeService.updateById(WbdTransportLineType);
        return dto;
    }

    /**
     * 删除线路类型
     *
     * @param id 线路类型id
     * @return 返回信息
     */
    @PutMapping("/{id}/disable")
    public Result disable(@PathVariable(name = "id") String id) {
        transportLineTypeService.disableById(id);
        return Result.ok();
    }
}
