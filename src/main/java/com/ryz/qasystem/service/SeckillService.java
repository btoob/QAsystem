package com.ryz.qasystem.service;

import com.ryz.qasystem.Utils.OrderRecord;
import com.ryz.qasystem.model.RespBean;
import com.ryz.qasystem.model.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.ryz.qasystem.Utils.SystemConst.CONST_SECKILLGOODS_ID_PREFIX;
import static com.ryz.qasystem.Utils.SystemConst.CONST_USER_ID_PREFIX;

@Service
public class SeckillService {

    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1,
            10l, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000));

    @Autowired
    RedisTemplate redisTemplate;

    public RespBean saveOrder(Integer id, Integer userId) {
        //判断用户是否已经抢过一次

        //从商品列表中取出商品id
        id = (Integer) redisTemplate.boundListOps(CONST_SECKILLGOODS_ID_PREFIX + id).rightPop();
        if (id == null) { //商品不存在
            return RespBean.error("已经被抢完了");
        }

        //根据id取出商品
        Seckill seckillGood = (Seckill) redisTemplate.boundHashOps(Seckill.class.getSimpleName()).get(id);
        //如果时间不对，提示抢购已经结束
        if (seckillGood.getEndTime().getTime() < System.currentTimeMillis()) {
            return RespBean.error("活动已经结束啦");
        }

        //到这一步就已经成功抢到了， 将该用户记录到抢购记录中
        redisTemplate.boundSetOps(CONST_USER_ID_PREFIX+id).add(userId);
        //保存下单信息，即商品id与用户id的关联类，保存在redis中，供多线程处理
        redisTemplate.boundListOps(OrderRecord.class.getSimpleName()).leftPush(new OrderRecord(id, userId));

        executor.execute(orderCreateThread);
        return RespBean.ok("抢购成功，如需支付，请支付");



    }
}
