package com.ryz.qasystem.service;

import com.ryz.qasystem.mapper.QuestionMapper;
import com.ryz.qasystem.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    public List<Question> getAllQuestions() {
        return questionMapper.getAllQuestions();
    }
}
