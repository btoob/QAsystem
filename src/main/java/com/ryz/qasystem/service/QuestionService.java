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

import java.util.*;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public RespPageBean getAllQuestionsByPage(String search, Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Question> data = questionMapper.getAllQuestionsByPage(search, page, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : data) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.getUserById(question.getUserId());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        Long totalNumQueston = questionMapper.getTotalNumQueston();
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(questionDTOS);
        pageBean.setTotal(totalNumQueston);
        return pageBean;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.getUserById(question.getUserId());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        String tag = question.getTag();
        String[] split = tag.split(",");
        questionDTO.setUser(user);
        questionDTO.setTag(split);
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
        question.setViewCount(question.getViewCount() + 1);
        question.setUpdateTime(new Date());
        questionMapper.incView(question);
    }

    public RespPageBean getUserQuestionsByPage(Integer page, Integer size, Integer id) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }

        List<Question> data = questionMapper.getUserQuestionsByPage(page, size, id);
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question question : data) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.getUserById(question.getUserId());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        Long totalNumQuestion = questionMapper.getTotalNumQuestonByUserId(id);
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(questionDTOs);
        pageBean.setTotal(totalNumQuestion);
        return pageBean;

    }

    public List<QuestionDTO> getRelatedQuestionByTag(String[] tags) {
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (String tag : tags) {
            List<Question> relatedQuestion = questionMapper.getRelatedQuestionByTag(tag);
            for (Question question : relatedQuestion) {
                //防止重复
                if (set.contains(question.getId())){
                    continue;
                }
                set.add(question.getId());
                User user = userMapper.getUserById(question.getUserId());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOs.add(questionDTO);
            }
        }
        return questionDTOs;
    }
}
