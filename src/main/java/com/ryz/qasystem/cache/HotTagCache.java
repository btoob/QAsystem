package com.ryz.qasystem.cache;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    }
}
