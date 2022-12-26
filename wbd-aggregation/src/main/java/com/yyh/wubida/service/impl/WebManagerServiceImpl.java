package com.yyh.wubida.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyh.wubida.DTO.DriverJobDTO;
import com.yyh.wubida.DTO.TaskPickupDispatchDTO;
import com.yyh.wubida.DTO.TaskTransportDTO;
import com.yyh.wubida.DTO.TransportOrderDTO;
import com.yyh.wubida.DTO.webManager.DriverJobQueryDTO;
import com.yyh.wubida.DTO.webManager.TaskPickupDispatchQueryDTO;
import com.yyh.wubida.DTO.webManager.TaskTransportQueryDTO;
import com.yyh.wubida.DTO.webManager.TransportOrderQueryDTO;
import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.mapper.WebManagerMapper;
import com.yyh.wubida.service.WebManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebManagerServiceImpl implements WebManagerService {

    @Autowired
    private WebManagerMapper webManagerMapper;

    @Override
    public PageResponse<DriverJobDTO> findDriverJobByPage(DriverJobQueryDTO dto) {
        IPage<DriverJobDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        webManagerMapper.findDriverJobByPage(iPage, dto);
        return PageResponse.<DriverJobDTO>builder()
                .counts(iPage.getTotal())
                .pages(iPage.getPages())
                .pagesize(dto.getPageSize())
                .page(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }

    @Override
    public PageResponse<TaskPickupDispatchDTO> findTaskPickupDispatchJobByPage(TaskPickupDispatchQueryDTO dto) {
        IPage<TaskPickupDispatchDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        webManagerMapper.findTaskPickupDispatchJobByPage(iPage, dto);
        return PageResponse.<TaskPickupDispatchDTO>builder()
                .counts(iPage.getTotal())
                .pages(iPage.getPages())
                .pagesize(dto.getPageSize())
                .page(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }

    @Override
    public PageResponse<TransportOrderDTO> findTransportOrderByPage(TransportOrderQueryDTO dto) {
        IPage<TransportOrderDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        webManagerMapper.findTransportOrderByPage(iPage, dto);
        return PageResponse.<TransportOrderDTO>builder()
                .counts(iPage.getTotal())
                .pages(iPage.getPages())
                .pagesize(dto.getPageSize())
                .page(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }

    @Override
    public PageResponse<TaskTransportDTO> findTaskTransportByPage(TaskTransportQueryDTO dto) {
        IPage<TaskTransportDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        webManagerMapper.findTaskTransportByPage(iPage, dto);
        return PageResponse.<TaskTransportDTO>builder()
                .counts(iPage.getTotal())
                .pages(iPage.getPages())
                .pagesize(dto.getPageSize())
                .page(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }
}
