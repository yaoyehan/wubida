package com.yyh.wubida.DTO;

import com.yyh.wubida.entity.CacheLineDetailEntity;
import lombok.Data;

@Data
public class CacheLineDetailDTO extends CacheLineDetailEntity {

    /**
     * 起始机构
     */
    private String startAgency;

    /**
     * 终点机构
     */
    private String endAgency;

}
