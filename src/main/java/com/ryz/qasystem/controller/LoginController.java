package com.ryz.qasystem.controller;

import com.ryz.qasystem.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("想访问该接口，得先登录，请登录");
    }

    @GetMapping("/index")
    public RespBean index(){
        return RespBean.error("url地址上不加#访问的是接口而不是页面");
    }


}
