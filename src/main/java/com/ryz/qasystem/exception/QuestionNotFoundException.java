package com.ryz.qasystem.exception;

import java.util.Map;

public class QuestionNotFoundException extends BaseException {

    public QuestionNotFoundException(Map<String, Object> data) {
        super(ErrorCode.QUESTION_NOT_FOUND, data);
    }
}
