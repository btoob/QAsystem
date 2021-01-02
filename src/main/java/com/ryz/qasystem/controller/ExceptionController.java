package com.ryz.qasystem.controller;

import com.ryz.qasystem.exception.QuestionNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExceptionController {

    @GetMapping("/question")
    public void throwException(){
        Map<String, Object> map = new HashMap<>();
        Object o = 100;
        map.put("字符串是多少：",o );
        throw new QuestionNotFoundException(map);
    }
}
