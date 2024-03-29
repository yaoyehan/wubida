package com.yyh.wubida.entity.agency;

import com.baomidou.mybatisplus.annotation.IdType;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 机构业务范围表
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Data
@TableName("wbd_agency_scope")
public class WbdAgencyScope implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 机构id
     */
    private String agencyId;

    /**
     * 行政区域id
     */
    private String areaId;

    /**
     * 多边形经纬度坐标集合
     */
    private String mutiPoints;
}
