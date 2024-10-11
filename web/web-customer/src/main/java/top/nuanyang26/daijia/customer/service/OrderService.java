package top.nuanyang26.daijia.customer.service;

import top.nuanyang26.daijia.model.form.customer.ExpectOrderForm;
import top.nuanyang26.daijia.model.form.customer.SubmitOrderForm;
import top.nuanyang26.daijia.model.form.map.CalculateDrivingLineForm;
import top.nuanyang26.daijia.model.form.payment.CreateWxPaymentForm;
import top.nuanyang26.daijia.model.vo.base.PageVo;
import top.nuanyang26.daijia.model.vo.customer.ExpectOrderVo;
import top.nuanyang26.daijia.model.vo.driver.DriverInfoVo;
import top.nuanyang26.daijia.model.vo.map.DrivingLineVo;
import top.nuanyang26.daijia.model.vo.map.OrderLocationVo;
import top.nuanyang26.daijia.model.vo.map.OrderServiceLastLocationVo;
import top.nuanyang26.daijia.model.vo.order.CurrentOrderInfoVo;
import top.nuanyang26.daijia.model.vo.order.OrderInfoVo;
import top.nuanyang26.daijia.model.vo.payment.WxPrepayVo;

public interface OrderService {

    //预估订单数据
    ExpectOrderVo expectOrder(ExpectOrderForm expectOrderForm);

    //乘客等待时无责取消订单
    Boolean customerCancelNoAcceptOrder(Long customerId, Long orderId);

    //乘客下单
    Long submitOrder(SubmitOrderForm submitOrderForm);

    //查询订单状态
    Integer getOrderStatus(Long orderId);

    //乘客查找当前订单
    CurrentOrderInfoVo searchCustomerCurrentOrder(Long customerId);

    OrderInfoVo getOrderInfo(Long orderId, Long customerId);

    DriverInfoVo getDriverInfo(Long orderId, Long customerId);

    OrderLocationVo getCacheOrderLocation(Long orderId);

    DrivingLineVo calculateDrivingLine(CalculateDrivingLineForm calculateDrivingLineForm);

    OrderServiceLastLocationVo getOrderServiceLastLocation(Long orderId);

    PageVo findCustomerOrderPage(Long customerId, Long page, Long limit);

    WxPrepayVo createWxPayment(CreateWxPaymentForm createWxPaymentForm);

    Boolean queryPayStatus(String orderNo);
}
