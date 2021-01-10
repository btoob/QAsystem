package com.ryz.qasystem.controller;

import com.ryz.qasystem.cache.TagCache;
import com.ryz.qasystem.dto.TagDTO;
import com.ryz.qasystem.dto.TagOptionDTO;
import com.ryz.qasystem.model.Question;
import com.ryz.qasystem.model.RespBean;
import com.ryz.qasystem.service.QuestionService;
import com.ryz.qasystem.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    QuestionService questionService;
    @Autowired
    TagService tagService;

    @PostMapping("/")
    public RespBean doPublish(@RequestBody Question question){
        if (questionService.addQuestion(question)==1){
            return RespBean.ok("发布成功 ");
        }
        return RespBean.error("发布失败");

    }

    @PutMapping("/update")
    public RespBean doUpdate(@RequestBody Question question){
        if (questionService.updateQuestion(question)==1){
            return RespBean.ok("修改成功", "aaaaaa");
        }
        return RespBean.error("修改失败");
    }

    @GetMapping("/getTags")
    public RespBean getTags(){
        List<TagOptionDTO> tagOption = tagService.getTagOption();
        return RespBean.ok("", tagOption);
    }
}
