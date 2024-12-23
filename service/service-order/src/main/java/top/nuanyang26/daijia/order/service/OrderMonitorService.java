package top.nuanyang26.daijia.order.service;

import top.nuanyang26.daijia.model.entity.order.OrderMonitor;
import top.nuanyang26.daijia.model.entity.order.OrderMonitorRecord;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderMonitorService extends IService<OrderMonitor> {

    Boolean saveOrderMonitorRecord(OrderMonitorRecord orderMonitorRecord);

    Long saveOrderMonitor(OrderMonitor orderMonitor);

    OrderMonitor getOrderMonitor(Long orderId);

    Boolean updateOrderMonitor(OrderMonitor orderMonitor);


}
