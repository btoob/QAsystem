package com.ryz.qasystem.schedule;

import com.ryz.qasystem.cache.HotTagCache;
import com.ryz.qasystem.dto.QuestionDTO;
import com.ryz.qasystem.mapper.QuestionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class HotTagTask {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 3)   //3小时一次
    public void hotTagSchedule() {
        log.info("hotTagSchedule start {}", new Date());

        Map<String, Integer> priorities = new HashMap<>();
        List<QuestionDTO> questions = questionMapper.getAllQuestionsByPage(null, null, null);
        for (QuestionDTO question:questions){
            String[] tags = question.getTag().split(",");
            for(String tag:tags){
                priorities.put(tag, priorities.getOrDefault(tag, 0)+1);
            }
        }

        hotTagCache.updateTags(priorities);
        log.info("hotTagSchedule stop {}", new Date());

    }
}
