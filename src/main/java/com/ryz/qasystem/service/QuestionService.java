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
        List<QuestionDTO> data = questionMapper.getAllQuestionsByPage(search, page, size);
        Long totalNumQueston = questionMapper.getTotalNumQueston(search);
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(data);
        pageBean.setTotal(totalNumQueston);
        return pageBean;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.getUserById(question.getUserId());
        user.setPassword(null);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        String tag = question.getTag();
        questionDTO.setUser(user);
        questionDTO.setTag(tag);
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

        List<QuestionDTO> data = questionMapper.getUserQuestionsByPage(page, size, id);
        Long totalNumQuestion = questionMapper.getTotalNumQuestonByUserId(id);
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(data);
        pageBean.setTotal(totalNumQuestion);
        return pageBean;

    }

    public List<QuestionDTO> getRelatedQuestionByTag(String[] tags) {
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (String tag : tags) {
//            List<Question> relatedQuestion = questionMapper.getRelatedQuestionByTag(tag);
            List<QuestionDTO> relatedQuestion = questionMapper.getRelatedQuestionByTag(tag);
            for (QuestionDTO questionDTO : relatedQuestion) {
                //防止重复
                if (set.contains(questionDTO.getId())){
                    continue;
                }
                set.add(questionDTO.getId());
                questionDTOs.add(questionDTO);
            }
        }
        return questionDTOs;
    }

    public RespPageBean getQuestionByTagByPage(Integer page, Integer size, String tag) {
        if (page!=null&&size!=null){
            page = (page-1)*size;
        }

        List<QuestionDTO> data = questionMapper.getQuestionByTagByPage(page, size, tag);
        Long totalNumQuestion = questionMapper.getTotalNumQuestonByTag(tag);
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(data);
        pageBean.setTotal(totalNumQuestion);
        return pageBean;
    }

    public RespPageBean getHotQuestionsByPage(Integer page, Integer size) {
        if (page!=null&&size!=null){
            page=(page-1)*size;
        }
        List<QuestionDTO> data = questionMapper.getHotQuestionsByPage(page,size);
        Long totalHotQuestion = questionMapper.getTotalHotQuestionNums();
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(data);
        pageBean.setTotal(totalHotQuestion);
        return pageBean;
    }

    public RespPageBean getNoReplyQuestionsByPage(Integer page, Integer size) {
        if (page!=null&&size!=null){
            page=(page-1)*size;
        }
        List<QuestionDTO> data = questionMapper.getNoReplyQuestionsByPage(page,size);
        Long totalNoReplyQuestion = questionMapper.getTotalNoReplyQuestionNums();
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(data);
        pageBean.setTotal(totalNoReplyQuestion);
        return pageBean;
    }
}
