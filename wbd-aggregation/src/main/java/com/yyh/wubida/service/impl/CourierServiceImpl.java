package com.yyh.wubida.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyh.wubida.DTO.AppCourierQueryDTO;
import com.yyh.wubida.DTO.TaskPickupDispatchDTO;
import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.mapper.CourierMapper;
import com.yyh.wubida.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierMapper courierMapper;

    @Override
    public PageResponse<TaskPickupDispatchDTO> findByPage(AppCourierQueryDTO dto) {
        IPage<TaskPickupDispatchDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        courierMapper.findByPage(iPage, dto);

        return PageResponse.<TaskPickupDispatchDTO>builder()
                .counts(iPage.getTotal())
                .pages(iPage.getPages())
                .pagesize(dto.getPageSize())
                .page(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }
}
