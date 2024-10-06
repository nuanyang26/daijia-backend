package top.nuanyang26.daijia.order.handle;

import jakarta.annotation.PostConstruct;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.nuanyang26.daijia.common.constant.RedisConstant;
import top.nuanyang26.daijia.order.service.OrderInfoService;

//监听延迟队列
@Component
public class RedisDelayHandle {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private OrderInfoService orderInfoService;

// 在依赖都创建完成后，再去加载这个方法
    @PostConstruct
    public void listener() {
        new Thread(()->{
            while(true) {
                //获取延迟队列里面阻塞队列
                RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue(RedisConstant.QUEUE_ORDER_CANCEL);

                //从队列获取消息
                try {
                    String orderId = blockingQueue.take();

                    //取消订单
                    if(StringUtils.hasText(orderId)) {
                        //调用方法取消订单
                        orderInfoService.orderCancel(Long.parseLong(orderId));
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }
}
