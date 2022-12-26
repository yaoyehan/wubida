package com.yyh.wubida.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyh.wubida.DTO.AppDriverQueryDTO;
import com.yyh.wubida.DTO.DriverJobDTO;
import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.mapper.DriverMapper;
import com.yyh.wubida.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverMapper driverMapper;

    @Override
    public PageResponse<DriverJobDTO> findByPage(AppDriverQueryDTO dto) {
        IPage<DriverJobDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        driverMapper.findByPage(iPage, dto);

        return PageResponse.<DriverJobDTO>builder()
                .counts(iPage.getTotal())
                .pages(iPage.getPages())
                .pagesize(dto.getPageSize())
                .page(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }
}
