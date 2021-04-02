package com.ryz.qasystem.mapper;

import com.ryz.qasystem.dto.SecondCommentDTO;
import com.ryz.qasystem.model.SecondComment;

import java.util.List;

public interface SecondCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecondComment record);

    int insertSelective(SecondComment record);

    SecondComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecondComment record);

    int updateByPrimaryKey(SecondComment record);

    List<SecondCommentDTO> getAllSecondCommentByParentId(Integer id);
}