package com.ryz.qasystem.mapper;

import com.ryz.qasystem.model.Seckill;

public interface SeckillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Seckill record);

    int insertSelective(Seckill record);

    Seckill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Seckill record);

    int updateByPrimaryKey(Seckill record);
}