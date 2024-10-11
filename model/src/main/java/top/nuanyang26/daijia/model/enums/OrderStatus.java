package top.nuanyang26.daijia.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum OrderStatus {
    WAITING_ACCEPT(1, "等待接单"),
    ACCEPTED(2, "已接单"),
    DRIVER_ARRIVED(3, "司机已到达"),
    UPDATE_CART_INFO(4, "更新代驾车辆信息"),
    START_SERVICE(5, "开始服务"),
    END_SERVICE(6, "结束服务"),
    UNPAID(7, "待付款"),
    PAID(8, "已付款"),
    FINISH(9, "订单已完成"),
    CUSTOMER_CANCEL(11, "乘客取消"),
    DRIVER_CANCEL(12, "司机取消"),
    ACCIDENT_CANCEL(13, "事故关闭"),
    RESTS(14, "其他"),
    CANCEL_ORDER(-1, "未接单取消订单"),
    NULL_ORDER(-100, "不存在"),
    ;

    @EnumValue
    private Integer status;
    private String comment;

    OrderStatus(Integer status, String comment) {
        this.status = status;
        this.comment = comment;
    }

    // 枚举类不需要Setter 设计的初衷就是为了创建一组固定的常量值
}