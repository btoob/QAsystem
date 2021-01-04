package com.ryz.qasystem.mapper;

import com.ryz.qasystem.model.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    void incCommentCount(Comment dbcomment);

    List<Comment> getAllComments();
}