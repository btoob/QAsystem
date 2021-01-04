package com.ryz.qasystem.service;


import com.ryz.qasystem.mapper.CommentMapper;
import com.ryz.qasystem.mapper.QuestionMapper;
import com.ryz.qasystem.model.Comment;
import com.ryz.qasystem.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    CommentMapper commentMapper;
    public boolean insert(Comment comment) {
        if (comment.getType()==1){   //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            comment.setCommentCount(0);
            int i = commentMapper.insertSelective(comment);

            //增加问题评论数
            question.setCommentCount(question.getCommentCount()+1);
            question.setUpdateTime(new Date());
            questionMapper.incCommentCount(question);
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

    public List<Comment> getAllComments() {
        return commentMapper.getAllComments();
    }
}
