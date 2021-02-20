package com.ryz.qasystem.mapper;

import com.ryz.qasystem.model.Secorder;

public interface SecorderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Secorder record);

    int insertSelective(Secorder record);

    Secorder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Secorder record);

    int updateByPrimaryKey(Secorder record);
}