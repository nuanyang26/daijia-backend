package top.nuanyang26.daijia.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.nuanyang26.daijia.model.entity.coupon.CouponInfo;
import top.nuanyang26.daijia.model.vo.coupon.NoReceiveCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.NoUseCouponVo;
import top.nuanyang26.daijia.model.vo.coupon.UsedCouponVo;

import java.util.List;

@Mapper
public interface CouponInfoMapper extends BaseMapper<CouponInfo> {

    IPage<NoReceiveCouponVo> findNoReceivePage(Page<CouponInfo> pageParam, @Param("customerId") Long customerId);

    IPage<NoUseCouponVo> findNoUsePage(Page<CouponInfo> pageParam, @Param("customerId") Long customerId);

    //5.1 更新领取数量
    int updateReceiveCount(Long couponId);

    //2 根据乘客id，获取乘客已经领取但是没有使用的优惠卷列表
    List<NoUseCouponVo> findNoUseList(@Param("customerId") Long customerId);

    IPage<UsedCouponVo> findUsedPage(Page<CouponInfo> pageParam, Long customerId);
}
