package com.yyh.wubida.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.common.CustomIdGenerator;
import com.yyh.wubida.mapper.user.WbdCourierScopMapper;
import com.yyh.wubida.entity.user.WbdCourierScope;
import com.yyh.wubida.service.user.IWbdCourierScopeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 快递员业务范围表 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Service
public class WbdCourierScopServiceImpl extends ServiceImpl<WbdCourierScopMapper, WbdCourierScope> implements IWbdCourierScopeService {
    @Autowired
    private CustomIdGenerator idGenerator;

    @Override
    public void batchSave(List<WbdCourierScope> scopeList) {
        scopeList.forEach(scope -> scope.setId(idGenerator.nextId(scope) + ""));
        saveBatch(scopeList);
    }

    @Override
    public void delete(String areaId, String userId) {
        LambdaQueryWrapper<WbdCourierScope> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        boolean canExecute = false;
        if (StringUtils.isNotEmpty(areaId)) {
            lambdaQueryWrapper.eq(WbdCourierScope::getAreaId, areaId);
            canExecute = true;
        }
        if (StringUtils.isNotEmpty(userId)) {
            lambdaQueryWrapper.eq(WbdCourierScope::getUserId, userId);
            canExecute = true;
        }
        if (canExecute) {
            baseMapper.delete(lambdaQueryWrapper);
        }
    }

    @Override
    public List<WbdCourierScope> findAll(String areaId, String userId) {
        LambdaQueryWrapper<WbdCourierScope> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(areaId)) {
            lambdaQueryWrapper.eq(WbdCourierScope::getAreaId, areaId);
        }
        if (StringUtils.isNotEmpty(userId)) {
            lambdaQueryWrapper.eq(WbdCourierScope::getUserId, userId);
        }
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
