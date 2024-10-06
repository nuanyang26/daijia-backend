package top.nuanyang26.daijia.order.service.impl;

import top.nuanyang26.daijia.model.entity.order.OrderMonitor;
import top.nuanyang26.daijia.model.entity.order.OrderMonitorRecord;
import top.nuanyang26.daijia.order.mapper.OrderMonitorMapper;
import top.nuanyang26.daijia.order.repository.OrderMonitorRecordRepository;
import top.nuanyang26.daijia.order.service.OrderMonitorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderMonitorServiceImpl extends ServiceImpl<OrderMonitorMapper, OrderMonitor> implements OrderMonitorService {

    @Autowired
    private OrderMonitorRecordRepository orderMonitorRecordRepository;
    @Autowired
    private OrderMonitorMapper orderMonitorMapper;

    @Override
    public Boolean saveOrderMonitorRecord(OrderMonitorRecord orderMonitorRecord) {
        orderMonitorRecordRepository.save(orderMonitorRecord);
        return true;
    }

    @Override
    public Long saveOrderMonitor(OrderMonitor orderMonitor) {
        orderMonitorMapper.insert(orderMonitor);
        return orderMonitor.getId();
    }

    @Override
    public OrderMonitor getOrderMonitor(Long orderId) {
        LambdaQueryWrapper<OrderMonitor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderMonitor::getOrderId, orderId);
        return this.getOne(wrapper);
    }

    @Override
    public Boolean updateOrderMonitor(OrderMonitor orderMonitor) {
        return this.updateById(orderMonitor);
    }


}
