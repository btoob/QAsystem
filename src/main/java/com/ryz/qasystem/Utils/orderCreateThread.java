package com.ryz.qasystem.Utils;

import com.ryz.qasystem.model.Seckill;
import com.ryz.qasystem.model.Secorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class orderCreateThread implements Runnable {

    private static String lockKey = "reduceStock";

    @Autowired
    RedisTemplate redisTemplate;




    @Override
    public void run() {
        //从redis中的订单信息记录(OrderRecord)中获得订单
        OrderRecord orderRecord = (OrderRecord) redisTemplate.boundListOps(OrderRecord.class.getSimpleName()).rightPop();
        //在缓存中根据商品id查到它的详细信息
        Seckill seckillGoods = (Seckill) redisTemplate.boundHashOps(Seckill.class.getSimpleName()).get(orderRecord.getId());

        Secorder secorder = new Secorder();
        secorder.setSeckillId(seckillGoods.getId());
        secorder.setUserId(orderRecord.getUserId());
        secorder.setName(seckillGoods.getName());
        secorder.setStatus((byte) 0);
        secorder.setStockCount(1);
        secorder.setCreateTime(new Date());

        redisTemplate.boundHashOps(Secorder.class.getSimpleName()).put(orderRecord.getUserId(), secorder);

        //商品库存减1
        //由于缓存中当前商品id的库存可能被其他线程修改，会产生线程安全问题，因此修改商品库存的代码需要加锁


    }
}
