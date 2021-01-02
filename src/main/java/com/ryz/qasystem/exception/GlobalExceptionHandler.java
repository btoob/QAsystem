package com.ryz.qasystem.exception;


import com.ryz.qasystem.model.RespErrorBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleAppException(BaseException ex, HttpServletRequest request) {
        RespErrorBean representation = new RespErrorBean(ex, request.getRequestURI());
        return new ResponseEntity<>(representation, new HttpHeaders(), ex.getErrorCode().getStatus());
    }
}
