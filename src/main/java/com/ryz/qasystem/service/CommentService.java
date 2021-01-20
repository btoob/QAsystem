package com.ryz.qasystem.service;


import com.ryz.qasystem.dto.CommentDTO;
import com.ryz.qasystem.mapper.CommentMapper;
import com.ryz.qasystem.mapper.NotificationMapper;
import com.ryz.qasystem.mapper.QuestionMapper;
import com.ryz.qasystem.mapper.UserMapper;
import com.ryz.qasystem.model.Comment;
import com.ryz.qasystem.model.Notification;
import com.ryz.qasystem.model.Question;
import com.ryz.qasystem.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    NotificationMapper notificationMapper;
    @Transactional
    public boolean insert(Comment comment) {
        if (comment.getType()==1){   //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            comment.setCommentCount(0);
            comment.setCreateTime(new Date());
            comment.setUpdateTime(new Date());
            comment.setLikeCount(0);
            int i = commentMapper.insertSelective(comment);

            //增加问题评论数
            question.setCommentCount(question.getCommentCount()+1);
            question.setUpdateTime(new Date());
            questionMapper.incCommentCount(question);

            //创建通知
            createNotify(comment, question);
            return i==1;
        }else{ //回复评论

            //对应的父评论
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParentId());
            //对应的问题
            Question question = questionMapper.selectByPrimaryKey(dbcomment.getParentId());

            comment.setCommentCount(0);
            int i = commentMapper.insertSelective(comment);
            //增加父评论数
            dbcomment.setCommentCount(dbcomment.getCommentCount()+1);
            dbcomment.setUpdateTime(new Date());
            commentMapper.incCommentCount(dbcomment);
            //增加问题评论数
            question.setCommentCount(question.getCommentCount()+1);
            question.setUpdateTime(new Date());
            questionMapper.incCommentCount(question);

            return i==1;
        }
    }

    private void createNotify(Comment comment, Question question){
        User user = userMapper.getUserById(comment.getCommentator());
        Notification notification = new Notification();
        notification.setCreateTime(new Date());
        notification.setStatus(0);
        notification.setNotifierId(comment.getCommentator());  //发出通知的人
        notification.setNotifierName(user.getName());
        notification.setReceiverId(question.getUserId());
        notification.setQuestionId(question.getId());
        notification.setQuestionTitle(question.getTitle());
        notificationMapper.insertSelective(notification);
    }

    public List<CommentDTO> getAllCommentsByQuestionId(Integer id) {
        List<Comment> allCommentsByQuestionId = commentMapper.getAllCommentsByQuestionId(id);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment:allCommentsByQuestionId){
            User user = userMapper.getUserById(comment.getCommentator());
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(user);
            commentDTOs.add(commentDTO);
        }
        return commentDTOs;
    }
}
