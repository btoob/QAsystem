package com.ryz.qasystem.controller;

import com.ryz.qasystem.model.Question;
import com.ryz.qasystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }
}
