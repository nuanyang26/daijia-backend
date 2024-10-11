package top.nuanyang26.daijia.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.nuanyang26.daijia.common.login.TonyLogin;
import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.common.util.AuthContextHolder;
import top.nuanyang26.daijia.customer.service.OrderService;
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

@Slf4j
@Tag(name = "订单API接口管理")
@RestController
@RequestMapping("/order")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "乘客端查找当前订单")
    @TonyLogin
    @GetMapping("/searchCustomerCurrentOrder")
    public Result<CurrentOrderInfoVo> searchCustomerCurrentOrder() {
        Long customerId = AuthContextHolder.getUserId();
        return Result.ok(orderService.searchCustomerCurrentOrder(customerId));
    }

    @Operation(summary = "预估订单数据")
    @TonyLogin
    @PostMapping("/expectOrder")
    public Result<ExpectOrderVo> expectOrder(@RequestBody ExpectOrderForm expectOrderForm) {
        return Result.ok(orderService.expectOrder(expectOrderForm));
    }

    @Operation(summary = "乘客下单")
    @TonyLogin
    @PostMapping("/submitOrder")
    public Result<Long> submitOrder(@RequestBody SubmitOrderForm submitOrderForm) {
        submitOrderForm.setCustomerId(AuthContextHolder.getUserId());
        return Result.ok(orderService.submitOrder(submitOrderForm));
    }

    @Operation(summary = "查询订单状态")
    @TonyLogin
    @GetMapping("/getOrderStatus/{orderId}")
    public Result<Integer> getOrderStatus(@PathVariable Long orderId) {
        return Result.ok(orderService.getOrderStatus(orderId));
    }

    @Operation(summary = "获取订单信息")
    @TonyLogin
    @GetMapping("/getOrderInfo/{orderId}")
    public Result<OrderInfoVo> getOrderInfo(@PathVariable Long orderId) {
        Long customerId = AuthContextHolder.getUserId();
        return Result.ok(orderService.getOrderInfo(orderId, customerId));
    }

    @Operation(summary = "根据订单id获取司机基本信息")
    @TonyLogin
    @GetMapping("/getDriverInfo/{orderId}")
    public Result<DriverInfoVo> getDriverInfo(@PathVariable Long orderId) {
        Long customerId = AuthContextHolder.getUserId();
        return Result.ok(orderService.getDriverInfo(orderId, customerId));
    }

    @Operation(summary = "司机赶往代驾起始点：获取订单经纬度位置")
    @TonyLogin
    @GetMapping("/getCacheOrderLocation/{orderId}")
    public Result<OrderLocationVo> getOrderLocation(@PathVariable Long orderId) {
        return Result.ok(orderService.getCacheOrderLocation(orderId));
    }

    @Operation(summary = "计算最佳驾驶线路")
    @TonyLogin
    @PostMapping("/calculateDrivingLine")
    public Result<DrivingLineVo> calculateDrivingLine(@RequestBody CalculateDrivingLineForm calculateDrivingLineForm) {
        return Result.ok(orderService.calculateDrivingLine(calculateDrivingLineForm));
    }

    @Operation(summary = "乘客等待时无责取消订单接口")
    @TonyLogin
    @GetMapping("/customerCancelNoAcceptOrder/{orderId}")
    public Result<Boolean> customerCancelNoAcceptOrder(@PathVariable Long orderId) {
        Long customerId = AuthContextHolder.getUserId();
        return Result.ok(orderService.customerCancelNoAcceptOrder(customerId, orderId));

    }

    @Operation(summary = "代驾服务：获取订单服务最后一个位置信息")
    @TonyLogin
    @GetMapping("/getOrderServiceLastLocation/{orderId}")
    public Result<OrderServiceLastLocationVo> getOrderServiceLastLocation(@PathVariable Long orderId) {
        return Result.ok(orderService.getOrderServiceLastLocation(orderId));
    }

    @Operation(summary = "获取乘客订单分页列表")
    @TonyLogin
    @GetMapping("findCustomerOrderPage/{page}/{limit}")
    public Result<PageVo> findCustomerOrderPage(
            @Parameter(name = "page", description = "当前页码", required = true)
            @PathVariable Long page,

            @Parameter(name = "limit", description = "每页记录数", required = true)
            @PathVariable Long limit) {
        Long customerId = AuthContextHolder.getUserId();
        PageVo pageVo = orderService.findCustomerOrderPage(customerId, page, limit);
        return Result.ok(pageVo);
    }

    @Operation(summary = "创建微信支付")
    @TonyLogin
    @PostMapping("/createWxPayment")
    public Result<WxPrepayVo> createWxPayment(@RequestBody CreateWxPaymentForm createWxPaymentForm) {
        Long customerId = AuthContextHolder.getUserId();
        createWxPaymentForm.setCustomerId(customerId);
        return Result.ok(orderService.createWxPayment(createWxPaymentForm));
    }

    @Operation(summary = "支付状态查询")
    @TonyLogin
    @GetMapping("/queryPayStatus/{orderNo}")
    public Result<Boolean> queryPayStatus(@PathVariable String orderNo) {
        return Result.ok(orderService.queryPayStatus(orderNo));
    }
}

