package com.ryz.qasystem.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    QUESTION_NOT_FOUND(2001, HttpStatus.NOT_FOUND, "你找到问题不在了，要不要换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002, HttpStatus.BAD_REQUEST,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, HttpStatus.BAD_REQUEST,"当前操作需要登录，请登陆后重试"),
    SYS_ERROR(2004, HttpStatus.BAD_REQUEST,"服务冒烟了，要不然你稍后再试试！！！"),
    TYPE_PARAM_WRONG(2005, HttpStatus.BAD_REQUEST,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, HttpStatus.BAD_REQUEST,"回复的评论不存在了，要不要换个试试？"),
    CONTENT_IS_EMPTY(2007, HttpStatus.BAD_REQUEST,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008, HttpStatus.BAD_REQUEST,"兄弟你这是读别人的信息呢？"),
    NOTIFICATION_NOT_FOUND(2009, HttpStatus.BAD_REQUEST,"消息莫非是不翼而飞了？"),
    FILE_UPLOAD_FAIL(2010, HttpStatus.BAD_REQUEST,"图片上传失败"),
    INVALID_INPUT(2011, HttpStatus.BAD_REQUEST,"非法输入"),
    INVALID_OPERATION(2012, HttpStatus.BAD_REQUEST,"兄弟，是不是走错房间了？"),
    ;

    private final int code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(int code, HttpStatus status, String message){
        this.code=code;
        this.status=status;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
