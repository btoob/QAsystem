package com.ryz.qasystem.schedule;

import com.ryz.qasystem.mapper.SeckillMapper;
import com.ryz.qasystem.model.Seckill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.ryz.qasystem.Utils.SystemConst.CONST_SECKILLGOODS_ID_PREFIX;

@Component
@Slf4j
public class SeckillGoodsToRedis {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SeckillMapper seckillMapper;

    @Scheduled(cron = "0 */1 * * * ?")   //一分钟一次
    public void importToRedis(){
        log.info("SeckillGoodsToRedis start {}", new Date());
        //查询抢购商品数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        List<Seckill> seckillGoods = seckillMapper.selectByDate(date);


        for (Seckill seckillGood : seckillGoods) {//将抢购商品依次存入redis

            //注意如果redis中已经有的商品，则不更新,只添加之前未加入过的秒杀商品   //主KEY->Seckill, 副 KEY ->商品id
            if(redisTemplate.boundHashOps(Seckill.class.getSimpleName()).get(seckillGood.getId()) == null){
                redisTemplate.boundHashOps(Seckill.class.getSimpleName()).put(seckillGood.getId(), seckillGood);
                //为每个秒杀商品生成一个长度为库存量大小,元素值为商品id的队列，用户每下一个单，队列就弹出一个元素
                createQueue(seckillGood);
            }
        }
        log.info("SeckillGoodsToRedis stop {}", new Date());

    }

    private void createQueue(Seckill seckillGood) {
        Long id = seckillGood.getId();
        for (int i = 0; i < seckillGood.getStockCount(); i++) {
            //list中所有商品统一前缀，再加以id进行区分，左进右出
            redisTemplate.boundListOps(CONST_SECKILLGOODS_ID_PREFIX + id).leftPush(id);
        }
    }
}
