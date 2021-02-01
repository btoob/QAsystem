package com.ryz.qasystem.cache;

import com.ryz.qasystem.dto.HotTagDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Component
public class HotTagCache {
    private List<String> hots = new ArrayList<>();

    public List<String> getHots() {
        return hots;
    }

    public void setHots(List<String> hots) {
        this.hots = hots;
    }

    public void updateTags(Map<String, Integer> tags){
        Integer max = 5;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>();

        tags.forEach((tag, priority)->{
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(tag);
            hotTagDTO.setPriority(priority);

            if (priorityQueue.size()<max){
                priorityQueue.add(hotTagDTO);
            }else{
                HotTagDTO peek = priorityQueue.peek();
                if (hotTagDTO.compareTo(peek)>0){
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });

        List<String> sortedTags = new ArrayList<>();
        HotTagDTO poll = priorityQueue.poll();
        while (poll!=null){
            sortedTags.add(0, poll.getName());   //最小的放在最前面
            poll=priorityQueue.poll();
        }

        hots = sortedTags;
    }
}
