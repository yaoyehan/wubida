package com.yyh.wubida.service.transportline.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.common.utils.Constant;
import com.yyh.wubida.mapper.transportline.WbdTransportLineTypeMapper;
import com.yyh.wubida.entity.transportline.WbdTransportLineType;
import com.yyh.wubida.service.transportline.IWbdTransportLineTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * 线路类型表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdTransportLineTypeServiceImpl extends ServiceImpl<WbdTransportLineTypeMapper, WbdTransportLineType>
        implements IWbdTransportLineTypeService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public WbdTransportLineType saveTransportLineType(WbdTransportLineType WbdTransportLineType) {
        WbdTransportLineType.setId(idGenerator.nextId(WbdTransportLineType) + "");
        WbdTransportLineType.setLastUpdateTime(LocalDateTime.now());
        baseMapper.insert(WbdTransportLineType);
        return WbdTransportLineType;
    }

    @Override
    public IPage<WbdTransportLineType> findByPage(Integer page, Integer pageSize, String typeNumber, String name,
                                                 Integer agencyType) {
        Page<WbdTransportLineType> iPage = new Page(page, pageSize);
        LambdaQueryWrapper<WbdTransportLineType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            lambdaQueryWrapper.like(WbdTransportLineType::getName, name);
        }
        if (StringUtils.isNotEmpty(typeNumber)) {
            lambdaQueryWrapper.like(WbdTransportLineType::getTypeNumber, typeNumber);
        }
        if (agencyType != null) {
            lambdaQueryWrapper.and(i -> i.eq(WbdTransportLineType::getStartAgencyType, agencyType).or()
                    .eq(WbdTransportLineType::getEndAgencyType, agencyType));

        }
        lambdaQueryWrapper.eq(WbdTransportLineType::getStatus, Constant.DATA_DEFAULT_STATUS);
        lambdaQueryWrapper.orderBy(true, true, WbdTransportLineType::getId);
        return baseMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    @Override
    public List<WbdTransportLineType> findAll(List<String> ids) {
        LambdaQueryWrapper<WbdTransportLineType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ids != null && ids.size() > 0) {
            lambdaQueryWrapper.in(WbdTransportLineType::getId, ids);
        }
        lambdaQueryWrapper.orderBy(true, true, WbdTransportLineType::getId);
        return baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void disableById(String id) {
        WbdTransportLineType WbdTransportLineType = new WbdTransportLineType();
        WbdTransportLineType.setId(id);
        WbdTransportLineType.setStatus(Constant.DATA_DISABLE_STATUS);
        baseMapper.updateById(WbdTransportLineType);
    }

}
