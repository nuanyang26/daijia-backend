package top.nuanyang26.daijia.customer.service;

import top.nuanyang26.daijia.model.vo.base.PageVo;
import top.nuanyang26.daijia.model.vo.coupon.AvailableCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.NoReceiveCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.NoUseCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.UsedCouponVo;

import java.util.List;

public interface CouponService  {

    PageVo<NoReceiveCouponVo> findNoReceivePage(Long customerId, Long page, Long limit);

    PageVo<NoUseCouponVo> findNoUsePage(Long customerId, Long page, Long limit);

    Boolean receive(Long customerId, Long couponId);

    List<AvailableCouponVo> findAvailableCoupon(Long customerId, Long orderId);

    PageVo<UsedCouponVo> findUsedPage(Long customerId, Long page, Long limit);
}
