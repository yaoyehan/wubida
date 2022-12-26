package com.yyh.wubida.feign.driver;

import com.yyh.wubida.DTO.AppDriverQueryDTO;
import com.yyh.wubida.DTO.DriverJobDTO;
import com.yyh.wubida.common.utils.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@FeignClient(name = "wbd-aggregation")
@RequestMapping("appDriver")
@ApiIgnore
public interface AppDriverFeign {
    /**
     * 分页查询司机任务
     *
     * @param dto
     * @return
     */
    @PostMapping("page")
    PageResponse<DriverJobDTO> findByPage(@RequestBody AppDriverQueryDTO dto);

}
