package com.ryz.qasystem.dto;

import java.util.List;

public class TagOptionDTO {
    private String label;
    private String value;
    private List<TagOptionDTO> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TagOptionDTO> getChildren() {
        return children;
    }

    public void setChildren(List<TagOptionDTO> children) {
        this.children = children;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
