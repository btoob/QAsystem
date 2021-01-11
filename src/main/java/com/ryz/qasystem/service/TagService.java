package com.ryz.qasystem.service;

import com.ryz.qasystem.cache.TagCache;
import com.ryz.qasystem.dto.TagDTO;
import com.ryz.qasystem.dto.TagOptionDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    public List<TagOptionDTO> getTagOption(){
        List<TagDTO> tagDTOs = TagCache.get();

        List<TagOptionDTO> tagOptionDTOs = new ArrayList<>();

        for(TagDTO tagDTO:tagDTOs){
            TagOptionDTO tagOptionDTO = new TagOptionDTO();
            tagOptionDTO.setLabel(tagDTO.getCategoryName());

            List<TagOptionDTO> childrenTagOptionDTOs = new ArrayList<>();
            List<String> tags = tagDTO.getTags();
            for (String s : tags) {
                TagOptionDTO childrenTagOptionDTO = new TagOptionDTO();
                childrenTagOptionDTO.setLabel(s);
                childrenTagOptionDTO.setValue(s);
                childrenTagOptionDTOs.add(childrenTagOptionDTO);
            }
            tagOptionDTO.setChildren(childrenTagOptionDTOs);
            tagOptionDTOs.add(tagOptionDTO);
        }
        return tagOptionDTOs;
    }

}
