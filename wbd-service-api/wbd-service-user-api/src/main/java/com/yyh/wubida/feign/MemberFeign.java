package com.yyh.wubida.feign;

import com.yyh.wubida.common.utils.PageResponse;
import com.yyh.wubida.common.utils.Result;
import com.yyh.wubida.entity.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@FeignClient(name = "wbd-user")
@RequestMapping("member")
@ApiIgnore
public interface MemberFeign {
    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("page")
    PageResponse<Member> page(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize);

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @PostMapping("")
    Result save(@RequestBody Member entity);

    /**
     * 修改
     *
     * @param id
     * @param entity
     * @return
     */
    @PutMapping("/{id}")
    Result update(@PathVariable(name = "id") String id, @RequestBody Member entity);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    Result del(@PathVariable(name = "id") String id);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    Member detail(@PathVariable(name = "id") String id);
}
