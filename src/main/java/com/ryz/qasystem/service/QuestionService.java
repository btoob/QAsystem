package com.ryz.qasystem.service;

import com.ryz.qasystem.mapper.QuestionMapper;
import com.ryz.qasystem.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    public List<Question> getAllQuestions() {
        return questionMapper.getAllQuestions();
    }

    public Question getQuestionById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    public Integer addQuestion(Question question) {
        Date date = new Date();
        question.setCreateTime(date);
        question.setUpdateTime(date);
        return questionMapper.insertSelective(question);
    }

    public Integer updateQuestion(Question question) {
        Question dbQuestion = questionMapper.selectByPrimaryKey(question.getId());
        dbQuestion.setUpdateTime(new Date());
        dbQuestion.setTitle(question.getTitle());
        dbQuestion.setDescription(question.getDescription());
        dbQuestion.setTag(question.getTag());
        return questionMapper.updateByPrimaryKeySelective(dbQuestion);
    }
}
