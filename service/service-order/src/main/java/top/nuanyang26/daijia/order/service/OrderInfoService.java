package top.nuanyang26.daijia.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.nuanyang26.daijia.model.entity.order.OrderInfo;
import top.nuanyang26.daijia.model.form.order.OrderInfoForm;
import top.nuanyang26.daijia.model.form.order.StartDriveForm;
import top.nuanyang26.daijia.model.form.order.UpdateOrderBillForm;
import top.nuanyang26.daijia.model.form.order.UpdateOrderCartForm;
import top.nuanyang26.daijia.model.vo.base.PageVo;
import top.nuanyang26.daijia.model.vo.order.*;

import java.math.BigDecimal;

public interface OrderInfoService extends IService<OrderInfo> {

    //乘客下单
    Long saveOrderInfo(OrderInfoForm orderInfoForm);

    //根据订单id获取订单状态
    Integer getOrderStatus(Long orderId);

    // 乘客等待时无责取消订单
    Boolean customerCancelNoAcceptOrder(Long customerId, Long orderId);

    //司机抢单
    Boolean robNewOrder(Long driverId, Long orderId);

    //乘客端查找当前订单
    CurrentOrderInfoVo searchCustomerCurrentOrder(Long customerId);

    CurrentOrderInfoVo searchDriverCurrentOrder(Long driverId);

    Boolean driverArriveStartLocation(Long orderId, Long driverId);

    Boolean updateOrderCart(UpdateOrderCartForm updateOrderCartForm);

    Boolean startDriver(StartDriveForm startDriveForm);

    Long getOrderNumByTime(Long driverId, String startTime, String endTime);

    Boolean endDrive(UpdateOrderBillForm updateOrderBillForm);

    //获取乘客订单分页列表
    PageVo findCustomerOrderPage(Page<OrderInfo> pageParam, Long customerId);

    PageVo findDriverOrderPage(Page<OrderInfo> pageParam, Long driverId);

    OrderBillVo getOrderBillInfo(Long orderId);

    OrderProfitsharingVo getOrderProfitsharing(Long orderId);

    Boolean sendOrderBillInfo(Long orderId, Long driverId);

    OrderPayVo getOrderPayVo(String orderNo, Long customerId);

    Boolean updateOrderPayStatus(String orderNo);

    OrderRewardVo getOrderRewardFee(String orderNo);

    ////调用方法取消订单
    void orderCancel(long parseLong);

    Boolean updateCouponAmount(Long orderId, BigDecimal couponAmount);
}
