package top.nuanyang26.daijia.dispatch.service;

import top.nuanyang26.daijia.model.vo.dispatch.NewOrderTaskVo;
import top.nuanyang26.daijia.model.vo.order.NewOrderDataVo;

import java.util.List;

public interface NewOrderService {

    ////创建并启动任务调度方法
    Long addAndStartTask(NewOrderTaskVo newOrderTaskVo);

    //执行任务：搜索附近代驾司机
    void executeTask(long jobId);

    List<NewOrderDataVo> findNewOrderQueueData(Long driverId);

    Boolean clearNewOrderQueueData(Long driverId);
}
