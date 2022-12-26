/**
 * Copyright (c) 2019 联智合创 All rights reserved.
 * <p>
 * http://www.witlinked.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.yyh.wubida.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.wubida.entity.CacheLineEntity;

/**
 * 缓冲线路
 *
 * @author
 */
public interface ICacheLineService extends IService<CacheLineEntity> {

    String check(String verifyKey);

    int saveAndUpdate(CacheLineEntity cacheLineEntity);

    Integer deleteOldAndGetNewVersion(String notEqualsLineStart, String notEqualsLineEnd);
}
