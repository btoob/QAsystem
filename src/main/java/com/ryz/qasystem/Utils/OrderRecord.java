package com.ryz.qasystem.Utils;

import java.io.Serializable;

public class OrderRecord implements Serializable {
    private Integer id;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public OrderRecord(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }
}
