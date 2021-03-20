package com.ryz.qasystem.mapper;

import com.ryz.qasystem.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    void incCommentCount(Comment dbcomment);

    List<Comment> getAllCommentsByQuestionId(Integer id);

    Integer updateLikeCount(@Param("id") Integer id, @Param("newLikeCount") Integer newLikeCount,
                            @Param("newDislikeCount") Integer newDislikeCount);
}