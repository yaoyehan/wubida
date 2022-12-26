package com.yyh.wubida.feign.courier;

import com.yyh.wubida.DTO.AppCourierQueryDTO;
import com.yyh.wubida.DTO.TaskPickupDispatchDTO;
import com.yyh.wubida.common.utils.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@FeignClient(name = "wbd-aggregation")
@RequestMapping("appCourier")
@ApiIgnore
public interface AppCourierFeign {
    /**
     * 分页查询快递员任务
     *
     * @param dto
     * @return
     */
    @PostMapping("page")
    PageResponse<TaskPickupDispatchDTO> findByPage(@RequestBody AppCourierQueryDTO dto);

}
