package com.ryz.qasystem.Utils;

import com.ryz.qasystem.mapper.SeckillMapper;
import com.ryz.qasystem.model.Seckill;
import com.ryz.qasystem.model.Secorder;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class OrderCreateThread implements Runnable {

    private static String lockKey = "reduceStock";

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    Redisson redisson;
    @Autowired
    SeckillMapper seckillMapper;


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
        //使用redis分布式锁
        RLock lock;
            lock = redisson.getLock(lockKey);
        try {
            lock.lock(30, TimeUnit.SECONDS);   //锁的有效期
            //再一次根据商品id在缓存中获取其信息，因为上面获取的可能已经被其他线程修改过了，不是最新的。
            seckillGoods = (Seckill) redisTemplate.boundHashOps(Seckill.class.getSimpleName()).get(orderRecord.getId());
            //减库存
            seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);

            //减完库存之后判断库存是否还大于0，大于0则更新缓存，小于0则删除，并更新到数据库
            if (seckillGoods.getStockCount() > 0) {
                redisTemplate.boundHashOps(Seckill.class.getSimpleName()).put(seckillGoods.getId(), seckillGoods);
            } else {
                redisTemplate.boundHashOps(Seckill.class.getSimpleName()).delete(seckillGoods.getId());
            }
            //更新数据库
            seckillMapper.updateByPrimaryKeySelective(seckillGoods);
        } finally {
            lock.unlock();
        }

    }
}
