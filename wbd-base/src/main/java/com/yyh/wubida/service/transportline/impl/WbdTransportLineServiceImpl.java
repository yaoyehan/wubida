package com.yyh.wubida.service.transportline.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.common.utils.Constant;
import com.yyh.wubida.mapper.transportline.WbdTransportLineMapper;
import com.yyh.wubida.entity.transportline.WbdTransportLine;
import com.yyh.wubida.service.transportline.IWbdTransportLineService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 线路表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdTransportLineServiceImpl extends ServiceImpl<WbdTransportLineMapper, WbdTransportLine>
        implements IWbdTransportLineService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public WbdTransportLine saveTransportLine(WbdTransportLine WbdTransportLine) {
        WbdTransportLine.setId(idGenerator.nextId(WbdTransportLine) + "");
        baseMapper.insert(WbdTransportLine);
        return WbdTransportLine;
    }

    @Override
    public IPage<WbdTransportLine> findByPage(Integer page, Integer pageSize, String lineNumber, String name,
                                             String transportLineTypeId) {
        Page<WbdTransportLine> iPage = new Page(page, pageSize);
        LambdaQueryWrapper<WbdTransportLine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            lambdaQueryWrapper.like(WbdTransportLine::getName, name);
        }
        if (StringUtils.isNotEmpty(lineNumber)) {
            lambdaQueryWrapper.like(WbdTransportLine::getLineNumber, lineNumber);
        }
        if (StringUtils.isNotEmpty(transportLineTypeId)) {
            lambdaQueryWrapper.eq(WbdTransportLine::getTransportLineTypeId, transportLineTypeId);

        }
        lambdaQueryWrapper.eq(WbdTransportLine::getStatus, Constant.DATA_DEFAULT_STATUS);
        lambdaQueryWrapper.orderBy(true, false, WbdTransportLine::getId);
        return baseMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    @Override
    public List<WbdTransportLine> findAll(List<String> ids, String agencyId, List<String> agencyIds) {
        LambdaQueryWrapper<WbdTransportLine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ids != null && ids.size() > 0) {
            lambdaQueryWrapper.in(WbdTransportLine::getId, ids);
        }
        if (StringUtils.isNotEmpty(agencyId)) {
            lambdaQueryWrapper.eq(WbdTransportLine::getAgencyId, agencyId);
        }
        if (agencyIds != null && agencyIds.size() > 0) {
            lambdaQueryWrapper.in(WbdTransportLine::getAgencyId, agencyIds);
        }
        lambdaQueryWrapper.eq(WbdTransportLine::getStatus, Constant.DATA_DEFAULT_STATUS);
        lambdaQueryWrapper.orderBy(true, false, WbdTransportLine::getId);
        return baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void disable(String id) {
        WbdTransportLine WbdTransportLine = new WbdTransportLine();
        WbdTransportLine.setId(id);
        WbdTransportLine.setStatus(Constant.DATA_DISABLE_STATUS);
        baseMapper.updateById(WbdTransportLine);
    }

}
