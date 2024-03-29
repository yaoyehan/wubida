package com.yyh.wubida.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 快递员业务范围表
 * </p>
 *
 * @author itcast
 * @since 2019-12-20
 */
@Data
@TableName("wbd_courier_scop")
public class WbdCourierScope implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 行政区域id
     */
    private String areaId;
    /**
     * 多边形经纬度坐标集合
     */
    private String mutiPoints;
}
