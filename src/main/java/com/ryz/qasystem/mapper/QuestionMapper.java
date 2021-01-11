package com.ryz.qasystem.mapper;

import com.ryz.qasystem.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKeyWithBLOBs(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> getAllQuestionsByPage(@Param("page") Integer page, @Param("size") Integer size);

    Long getTotalNumQueston();

    void incView(Question question);

    void incCommentCount(Question question);

    List<Question> getUserQuestionsByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("id") Integer id);

    Long getTotalNumQuestonByUserId(Integer id);
}