package com.yyh.wubida.DTO;

import com.yyh.wubida.entity.CacheLineEntity;
import lombok.Data;

import java.util.List;

@Data
public class CacheLineDTO extends CacheLineEntity {

    private List<CacheLineDetailDTO> cacheLineDetailDTOS;

}
