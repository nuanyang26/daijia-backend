package top.nuanyang26.daijia.order.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import top.nuanyang26.daijia.common.constant.RedisConstant;
import top.nuanyang26.daijia.common.execption.TonyException;
import top.nuanyang26.daijia.common.result.ResultCodeEnum;

import java.util.concurrent.TimeUnit;

/**
 * 加锁的摸板代码，对给定订单进行加锁
 */
@Configuration
public class LockTemplateSupport {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    public void executeWithLockByOrderId(Long waitTimeout, Long lockTimeout, TimeUnit unit, Long orderId, Runnable task) {
        //判断订单是否存在，通过Redis，减少数据库压力
        if (Boolean.FALSE.equals(redisTemplate.hasKey(RedisConstant.ORDER_LOCK_MARK + orderId))) {
            //加锁失败
            throw new TonyException(ResultCodeEnum.LOCK_ORDER_FAIL);
        }

        //创建锁
        RLock lock = redissonClient.getLock(RedisConstant.ORDER_LOCK_MARK + orderId);

        try {
            //获取锁
            boolean flag = lock.tryLock(waitTimeout, lockTimeout, unit);
            if (flag) {
                if (Boolean.FALSE.equals(redisTemplate.hasKey(RedisConstant.ORDER_LOCK_MARK + orderId))) {
                    //抢单失败
                    throw new TonyException(ResultCodeEnum.LOCK_ORDER_FAIL);
                }

                // 实际方法执行
                task.run();

                //删除抢单标识
                redisTemplate.delete(RedisConstant.ORDER_LOCK_MARK + orderId);
            }
        } catch (Exception e) {
            //加锁失败
            throw new TonyException(ResultCodeEnum.LOCK_ORDER_FAIL);
        } finally {
            //释放
            if (lock.isLocked()) {
                lock.unlock();
            }
        }

    }
}
