package com.ryz.qasystem.dto;

import com.ryz.qasystem.model.Question;
import com.ryz.qasystem.model.User;


public class QuestionDTO extends Question {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
