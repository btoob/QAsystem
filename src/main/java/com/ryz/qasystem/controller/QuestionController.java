package com.ryz.qasystem.controller;

import com.ryz.qasystem.cache.HotTagCache;
import com.ryz.qasystem.dto.HotTagDTO;
import com.ryz.qasystem.dto.QuestionDTO;
import com.ryz.qasystem.model.RespPageBean;
import com.ryz.qasystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    HotTagCache hotTagCache;

    @GetMapping("/")
    public RespPageBean getAllQuestionsByPage(@RequestParam(required = false) String search, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        return questionService.getAllQuestionsByPage(search, page, size);
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable Integer id){
        questionService.incView(id);
        return questionService.getQuestionById(id);
    }

    @GetMapping("/profile/{id}")   //个人的所有问题
    public RespPageBean getUserQuestionsByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, @PathVariable Integer id){
        return questionService.getUserQuestionsByPage(page, size, id);
    }

    @GetMapping("/related")
    public List<QuestionDTO> getRelatedQuestionByTag(@RequestParam("tags") String[] tags){
        return questionService.getRelatedQuestionByTag(tags);
    }

    @GetMapping("/hotTags")
    public List<String> getHotTags(){
        return hotTagCache.getHots();
    }
}
