package com.ryz.qasystem.mapper;

import com.ryz.qasystem.dto.QuestionDTO;
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

    List<QuestionDTO> getAllQuestionsByPage(@Param("search") String search, @Param("page") Integer page, @Param("size") Integer size);

    Long getTotalNumQueston(String search);

    void incView(Question question);

    void incCommentCount(Question question);

    List<QuestionDTO> getUserQuestionsByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("id") Integer id);

    Long getTotalNumQuestonByUserId(Integer id);

    List<QuestionDTO> getRelatedQuestionByTag(String tag);

    List<QuestionDTO> getQuestionByTagByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("tag") String tag);

    Long getTotalNumQuestonByTag(String tag);

    List<QuestionDTO> getNoReplyQuestionsByPage(Integer page, Integer size);

    Long getTotalReplyQuestionNums();
}