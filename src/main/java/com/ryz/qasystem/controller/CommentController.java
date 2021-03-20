package com.ryz.qasystem.controller;

import com.ryz.qasystem.dto.CommentDTO;
import com.ryz.qasystem.model.Comment;
import com.ryz.qasystem.model.RespBean;
import com.ryz.qasystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/{id}")
    public List<CommentDTO> getAllCommentsByQuestionId(@PathVariable Integer id){
        return commentService.getAllCommentsByQuestionId(id);
    }

    @PostMapping("/")
    public RespBean doComment(@RequestBody Comment comment){
        if (commentService.insert(comment)){
            return RespBean.ok("评论成功");
        }
        return RespBean.error("评论失败");
    }

    @PutMapping("/{id}")
    public RespBean updateLikeCount(@PathVariable Integer id, @RequestParam Integer newLikeCount, @RequestParam Integer newDislikeCount){
        if (commentService.updateLikeCount(id, newLikeCount, newDislikeCount)==1){
            return RespBean.ok("更新点赞数成功");
        }
        return RespBean.error("更新点赞数失败");
    }
}