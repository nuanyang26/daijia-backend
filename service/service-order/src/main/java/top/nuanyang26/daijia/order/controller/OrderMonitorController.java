package top.nuanyang26.daijia.order.controller;

import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.model.entity.order.OrderMonitor;
import top.nuanyang26.daijia.model.entity.order.OrderMonitorRecord;
import top.nuanyang26.daijia.order.service.OrderMonitorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/monitor")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderMonitorController {

    @Autowired
    private OrderMonitorService orderMonitorService;

    @Operation(summary = "保存订单监控数据记录 - 图片")
    @PostMapping("/saveOrderMonitorRecord")
    public Result<Boolean> saveMonitorRecord(@RequestBody OrderMonitorRecord orderMonitorRecord) {
        return Result.ok(orderMonitorService.saveOrderMonitorRecord(orderMonitorRecord));
    }

    @Operation(summary = "保存订单审核数据记录 - 该订单需要审核的个数、是否报警")
    @PostMapping("/saveOrderMonitor")
    public Result<Long> saveMonitorRecord(@RequestBody OrderMonitor orderMonitor) {
        return Result.ok(orderMonitorService.saveOrderMonitor(orderMonitor));
    }

    // 根据订单id获取订单监控信息
    @Operation(summary = "根据订单id获取订单监控信息")
    @GetMapping("/getOrderMonitor/{orderId}")
    public Result<OrderMonitor> getOrderMonitor(@PathVariable Long orderId) {
        return Result.ok(orderMonitorService.getOrderMonitor(orderId));
    }

    // 更新订单监控记录
    @Operation(summary = "更新订单监控记录")
    @GetMapping("/updateOrderMonitor")
    public Result<Boolean> updateOrderMonitor(@RequestBody OrderMonitor orderMonitor) {
        return Result.ok(orderMonitorService.updateOrderMonitor(orderMonitor));
    }

}

