package com.yyh.wubida.task;
import com.yyh.wubida.DTO.OrderClassifyGroupDTO;
import com.yyh.wubida.DTO.OrderLineSimpleDTO;
import com.yyh.wubida.DTO.OrderLineTripsTruckDriverDTO;
import com.yyh.wubida.DTO.TaskTransportDTO;
import com.yyh.wubida.service.IBusinessOperationService;
import com.yyh.wubida.service.ITaskOrderClassifyService;
import com.yyh.wubida.service.ITaskRoutePlanningService;
import com.yyh.wubida.service.ITaskTripsSchedulingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
/**
 * 调度任务，每个机构都有一个任务
 */
@Slf4j
@Component("dispatchTask")
public class DispatchTask {
    @Autowired
    private ITaskOrderClassifyService orderClassifyService;
    @Autowired
    private ITaskTripsSchedulingService truckSchedulingService;
    @Autowired
    private ITaskRoutePlanningService routePlanningService;
    @Autowired
    private IBusinessOperationService businessOperationService;

    @SneakyThrows
    public void run(String businessId, String params, String jobId, String logId) {
        String LOGID = businessId + System.currentTimeMillis();
        log.info("[{}]TestTask定时任务正在执行，参数为：{},{},{},{}", LOGID, businessId, params, jobId, logId);

        // 订单分类
        List<OrderClassifyGroupDTO> orderClassifys = orderClassifyService.execute(businessId, jobId, logId);
        log.info("[{}] 订单分类完成:{}", LOGID, orderClassifys);

        // 路线规划
        List<OrderLineSimpleDTO> orderLineSimpleDTOS = routePlanningService.execute(orderClassifys, businessId, jobId, logId, params);
        log.info("[{}] 路线规划完成:{}", LOGID, orderLineSimpleDTOS);

        //  创建运输任务； 关联运单
        Map<String, TaskTransportDTO> transportTaskMap = businessOperationService.createTransportOrderTask(orderLineSimpleDTOS);
        log.info("[{}] 运输任务创建完成:{}", LOGID, transportTaskMap);

        // 车次 车辆 司机
        List<OrderLineTripsTruckDriverDTO> orderLineTripsTruckDriverDTOS = truckSchedulingService.execute(orderLineSimpleDTOS, businessId, jobId, logId, params);
        log.info("[{}] 规划车次车辆司机:{}", LOGID, orderLineTripsTruckDriverDTOS);

        //  完善运输任务信息； 创建司机作业任务；
        businessOperationService.updateTransportTask(orderLineTripsTruckDriverDTOS, transportTaskMap);
        log.info("[{}] 安排车次车辆司机,当前机构调度结束！！！！！", LOGID);
    }
}
