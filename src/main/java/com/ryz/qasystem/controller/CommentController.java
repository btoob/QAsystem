package com.ryz.qasystem.controller;

import com.ryz.qasystem.model.Comment;
import com.ryz.qasystem.model.RespBean;
import com.ryz.qasystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @PostMapping("/")
    public RespBean doComment(Comment comment){
        if (commentService.insert(comment)){
            return RespBean.ok("评论成功");
        }
        return RespBean.error("评论失败");
    }
}
