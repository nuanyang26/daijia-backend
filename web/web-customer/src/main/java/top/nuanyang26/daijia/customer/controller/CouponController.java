package top.nuanyang26.daijia.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.nuanyang26.daijia.common.login.TonyLogin;
import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.common.util.AuthContextHolder;
import top.nuanyang26.daijia.customer.service.CouponService;
import top.nuanyang26.daijia.model.vo.base.PageVo;
import top.nuanyang26.daijia.model.vo.coupon.AvailableCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.NoReceiveCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.NoUseCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.UsedCouponVo;

import java.util.List;


@Tag(name = "优惠券活动接口管理")
@RestController
@RequestMapping(value="/coupon")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Operation(summary = "查询未领取优惠券分页列表")
    @TonyLogin
    @GetMapping("findNoReceivePage/{page}/{limit}")
    public Result<PageVo<NoReceiveCouponVo>> findNoReceivePage(
            @Parameter(name = "page", description = "当前页码", required = true)
            @PathVariable Long page,

            @Parameter(name = "limit", description = "每页记录数", required = true)
            @PathVariable Long limit) {
        Long customerId = AuthContextHolder.getUserId();
        PageVo<NoReceiveCouponVo> pageVo = couponService.findNoReceivePage(customerId, page, limit);
        return Result.ok(pageVo);
    }

    @Operation(summary = "查询未使用优惠券分页列表")
    @TonyLogin
    @GetMapping("findNoUsePage/{page}/{limit}")
    public Result<PageVo<NoUseCouponVo>> findNoUsePage(
            @Parameter(name = "page", description = "当前页码", required = true)
            @PathVariable Long page,

            @Parameter(name = "limit", description = "每页记录数", required = true)
            @PathVariable Long limit) {
        Long customerId = AuthContextHolder.getUserId();
        PageVo<NoUseCouponVo> pageVo = couponService.findNoUsePage(customerId, page, limit);
        return Result.ok(pageVo);
    }

    @Operation(summary = "领取优惠券")
    @TonyLogin
    @GetMapping("/receive/{couponId}")
    public Result<Boolean> receive(@PathVariable Long couponId) {
        Long customerId = AuthContextHolder.getUserId();
        return Result.ok(couponService.receive(customerId, couponId));
    }

    @Operation(summary = "获取未使用的最佳优惠券信息")
    @TonyLogin
    @GetMapping("/findAvailableCoupon/{orderId}")
    public Result<List<AvailableCouponVo>> findAvailableCoupon(@PathVariable Long orderId) {
        Long customerId = AuthContextHolder.getUserId();
        return Result.ok(couponService.findAvailableCoupon(customerId, orderId));
    }

    @Operation(summary = "查询已使用优惠券分页列表")
    @TonyLogin
    @GetMapping("findUsedPage/{page}/{limit}")
    public Result<PageVo<UsedCouponVo>> findUsedPage(
            @Parameter(name = "page", description = "当前页码", required = true)
            @PathVariable Long page,

            @Parameter(name = "limit", description = "每页记录数", required = true)
            @PathVariable Long limit) {
        Long customerId = AuthContextHolder.getUserId();
        PageVo<UsedCouponVo> pageVo = couponService.findUsedPage(customerId, page, limit);
        return Result.ok(pageVo);
    }
}

