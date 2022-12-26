package com.yyh.wubida.controller.transportline;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yyh.wubida.DTO.transportline.TransportLineDto;
import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.common.utils.Result;
import com.yyh.wubida.entity.transportline.WbdTransportLine;
import com.yyh.wubida.service.transportline.IWbdTransportLineService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TransportLineController
 */
@RestController
@RequestMapping("base/transportLine")
public class TransportLineController {
    @Autowired
    private IWbdTransportLineService transportLineService;

    /**
     * 添加线路
     *
     * @param dto 线路信息
     * @return 线路信息
     */
    @PostMapping("")
    public TransportLineDto saveTransportLine(@RequestBody TransportLineDto dto) {
        WbdTransportLine WbdTransportLine = new WbdTransportLine();
        BeanUtils.copyProperties(dto, WbdTransportLine);
        WbdTransportLine = transportLineService.saveTransportLine(WbdTransportLine);
        BeanUtils.copyProperties(WbdTransportLine, dto);
        return dto;
    }

    /**
     * 根据id获取线路详情
     *
     * @param id 线路id
     * @return 线路详情
     */
    @GetMapping("/{id}")
    public TransportLineDto fineById(@PathVariable(name = "id") String id) {
        WbdTransportLine WbdTransportLine = transportLineService.getById(id);
        TransportLineDto dto = new TransportLineDto();
        if (WbdTransportLine != null) {
            BeanUtils.copyProperties(WbdTransportLine, dto);
        }else {
            dto.setId(id);
        }
        return dto;
    }

    /**
     * 获取线路分页信息
     *
     * @param page                页码
     * @param pageSize            页尺寸
     * @param lineNumber          线路编号
     * @param name                线路名称
     * @param transportLineTypeId 线路类型id
     * @return 线路分页信息
     */
    @GetMapping("/page")
    public PageResponse<TransportLineDto> findByPage(@RequestParam(name = "page") Integer page,
                                                     @RequestParam(name = "pageSize") Integer pageSize,
                                                     @RequestParam(name = "lineNumber", required = false) String lineNumber,
                                                     @RequestParam(name = "name", required = false) String name,
                                                     @RequestParam(name = "transportLineTypeId", required = false) String transportLineTypeId) {
        IPage<WbdTransportLine> transportLinePage = transportLineService.findByPage(page, pageSize, lineNumber, name, transportLineTypeId);
        List<TransportLineDto> dtoList = new ArrayList<>();
        transportLinePage.getRecords().forEach(WbdTransportLine -> {
            TransportLineDto dto = new TransportLineDto();
            BeanUtils.copyProperties(WbdTransportLine, dto);
            dtoList.add(dto);
        });
        return PageResponse.<TransportLineDto>builder().items(dtoList).pagesize(pageSize).page(page)
                .counts(transportLinePage.getTotal()).pages(transportLinePage.getPages()).build();
    }

    /**
     * 获取线路列表
     *
     * @param ids 线路id列表
     * @return 线路列表
     */
    @GetMapping("")
    public List<TransportLineDto> findAll(@RequestParam(name = "ids", required = false) List<String> ids,
                                          @RequestParam(name = "agencyId", required = false) String agencyId,
                                          @RequestParam(name = "agencyIds", required = false) List<String> agencyIds) {
        return transportLineService.findAll(ids, agencyId, agencyIds).stream().map(WbdTransportLine -> {
            TransportLineDto dto = new TransportLineDto();
            BeanUtils.copyProperties(WbdTransportLine, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * 更新线路信息
     *
     * @param id  线路id
     * @param dto 线路信息
     * @return 线路信息
     */
    @PutMapping("/{id}")
    public TransportLineDto uWbdate(@PathVariable(name = "id") String id, @RequestBody TransportLineDto dto) {
        dto.setId(id);
        WbdTransportLine WbdTransportLine = new WbdTransportLine();
        BeanUtils.copyProperties(dto, WbdTransportLine);
        transportLineService.updateById(WbdTransportLine);
        return dto;
    }

    /**
     * 删除线路
     *
     * @param id 线路id
     * @return 返回信息
     */
    @PutMapping("/{id}/disable")
    public Result disable(@PathVariable(name = "id") String id) {
        transportLineService.disable(id);
        return Result.ok();
    }


    /**
     * 获取线路列表
     *
     * @return 线路列表
     */
    @PostMapping("list")
    public List<TransportLineDto> list(@RequestBody TransportLineDto transportLineDto) {
        LambdaQueryWrapper<WbdTransportLine> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(StringUtils.isNotEmpty(transportLineDto.getStartAgencyId()), WbdTransportLine::getStartAgencyId, transportLineDto.getStartAgencyId());
        wrapper.eq(StringUtils.isNotEmpty(transportLineDto.getEndAgencyId()), WbdTransportLine::getEndAgencyId, transportLineDto.getEndAgencyId());
        wrapper.eq(StringUtils.isNotEmpty(transportLineDto.getAgencyId()), WbdTransportLine::getAgencyId, transportLineDto.getAgencyId());
        wrapper.eq(null != (transportLineDto.getStatus()), WbdTransportLine::getStatus, transportLineDto.getStatus());

        return transportLineService.list(wrapper).stream().map(WbdTransportLine -> {
            TransportLineDto dto = new TransportLineDto();
            BeanUtils.copyProperties(WbdTransportLine, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}