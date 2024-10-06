package top.nuanyang26.daijia.coupon.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.nuanyang26.daijia.model.entity.coupon.CouponInfo;
import top.nuanyang26.daijia.model.form.coupon.UseCouponForm;
import top.nuanyang26.daijia.model.vo.base.PageVo;
import top.nuanyang26.daijia.model.vo.coupon.AvailableCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.NoReceiveCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.NoUseCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.UsedCouponVo;

import java.math.BigDecimal;
import java.util.List;

public interface CouponInfoService extends IService<CouponInfo> {

    PageVo<NoReceiveCouponVo> findNoReceivePage(Page<CouponInfo> pageParam, Long customerId);

    PageVo<NoUseCouponVo> findNoUsePage(Page<CouponInfo> pageParam, Long customerId);

    Boolean receive(Long customerId, Long couponId);

    List<AvailableCouponVo> findAvailableCoupon(Long customerId, BigDecimal orderAmount);

    BigDecimal useCoupon(UseCouponForm useCouponForm);

    PageVo<UsedCouponVo> findUsedPage(Page<CouponInfo> pageParam, Long customerId);
}
