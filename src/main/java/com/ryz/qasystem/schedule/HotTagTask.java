package com.ryz.qasystem.schedule;

import com.ryz.qasystem.mapper.QuestionMapper;
import com.ryz.qasystem.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class HotTagTask {

    @Autowired
    QuestionMapper questionMapper;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");



    @Scheduled(fixedRate = 1000 * 60 * 60 * 3)
    public void hotTagSchedule() {
        log.info("The time is now {}", dateFormat.format(new Date()));

        Map<String, Integer> priorities = new HashMap<>();
        List<Question> questions = questionMapper.getAllQuestionsByPage(null, null, null);
        for (Question question:questions){

        }

    }
}
