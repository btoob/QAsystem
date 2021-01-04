package com.ryz.qasystem.service;

import com.ryz.qasystem.dto.QuestionDTO;
import com.ryz.qasystem.mapper.QuestionMapper;
import com.ryz.qasystem.mapper.UserMapper;
import com.ryz.qasystem.model.Question;
import com.ryz.qasystem.model.RespPageBean;
import com.ryz.qasystem.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public RespPageBean getAllQuestionsByPage(Integer page, Integer size) {
        if (page!=null && size!=null){
            page=(page-1)*size;
        }
        List<Question> data = questionMapper.getAllQuestionsByPage(page, size);
        Long totalNumQueston = questionMapper.getTotalNumQueston();
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(data);
        pageBean.setTotal(totalNumQueston);
        return pageBean;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.getUserById(question.getUserId());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
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

    public void incView(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        question.setViewCount(question.getViewCount()+1);
        question.setUpdateTime(new Date());
        questionMapper.incView(question);
    }
}
