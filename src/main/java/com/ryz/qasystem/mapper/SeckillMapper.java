package com.ryz.qasystem.mapper;

import com.ryz.qasystem.model.Seckill;

import java.util.List;

public interface SeckillMapper {

    List<Seckill> selectByDate(String date);

    int deleteByPrimaryKey(Long id);

    int insert(Seckill record);

    int insertSelective(Seckill record);

    Seckill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Seckill record);

    int updateByPrimaryKey(Seckill record);
}