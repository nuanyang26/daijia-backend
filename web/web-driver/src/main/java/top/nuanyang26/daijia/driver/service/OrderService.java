package top.nuanyang26.daijia.driver.service;

import top.nuanyang26.daijia.model.form.map.CalculateDrivingLineForm;
import top.nuanyang26.daijia.model.form.order.OrderFeeForm;
import top.nuanyang26.daijia.model.form.order.StartDriveForm;
import top.nuanyang26.daijia.model.form.order.UpdateOrderCartForm;
import top.nuanyang26.daijia.model.vo.base.PageVo;
import top.nuanyang26.daijia.model.vo.map.DrivingLineVo;
import top.nuanyang26.daijia.model.vo.order.CurrentOrderInfoVo;
import top.nuanyang26.daijia.model.vo.order.NewOrderDataVo;
import top.nuanyang26.daijia.model.vo.order.OrderInfoVo;

import java.util.List;

public interface OrderService {

    Integer getOrderStatus(Long orderId);

    List<NewOrderDataVo> findNewOrderQueueData(Long driverId);

    Boolean robNewOrder(Long driverId, Long orderId);

    CurrentOrderInfoVo searchDriverCurrentOrder(Long driverId);

    OrderInfoVo getOrderInfo(Long orderId, Long driverId);

    DrivingLineVo calculateDrivingLine(CalculateDrivingLineForm calculateDrivingLineForm);

    Boolean driverArriveStartLocation(Long orderId, Long driverId);

    Boolean updateOrderCart(UpdateOrderCartForm updateOrderCartForm);

    Boolean startDrive(StartDriveForm startDriveForm);

    Boolean endDrive(OrderFeeForm orderFeeForm);

    PageVo findDriverOrderPage(Long driverId, Long page, Long limit);

    Boolean sendOrderBillInfo(Long orderId, Long driverId);
}
