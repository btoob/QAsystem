package com.ryz.qasystem.Utils;

import java.io.Serializable;

public class OrderRecord implements Serializable {
    private Long id;
    private Integer userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public OrderRecord(Long id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }
}
