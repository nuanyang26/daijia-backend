package top.nuanyang26.daijia.order.client;

import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.model.entity.order.OrderMonitor;
import top.nuanyang26.daijia.model.entity.order.OrderMonitorRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "service-order")
public interface OrderMonitorFeignClient {

    /**
     * 保存订单监控数据记录 - 图片
     * @param orderMonitorRecord
     * @return
     */
    @PostMapping("/order/monitor/saveOrderMonitorRecord")
    Result<Boolean> saveMonitorRecord(@RequestBody OrderMonitorRecord orderMonitorRecord);

    /**
     * 保存订单审核数据记录 - 该订单需要审核的个数、是否报警
     * @param orderMonitor
     * @return
     */
    @PostMapping("/order/monitor/saveOrderMonitor")
    Result<Long> saveMonitorRecord(@RequestBody OrderMonitor orderMonitor);

    /**
     * 根据订単id获取订单监控信息
     */
    @GetMapping("/order/monitor/gétorderMonitor/{orderId}")
    Result<OrderMonitor> getOrderMonitor(@PathVariable Long orderId);

    /**
     * 跟新订单监控信息
     * @param orderMonitor
     * @return
     */
    @PostMapping("/order/monitor/updateOrderMonitor")
    Result<Boolean> updateOrderMonitor(@RequestBody OrderMonitor orderMonitor);

}