package com.ryz.qasystem.controller;

import com.ryz.qasystem.model.RespBean;
import com.ryz.qasystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public RespBean login(String username, String password){
        if (userService.loadUserByNameAndPwd(username, password)){
            return RespBean.ok("登录成功");
        }
        return RespBean.error("用户名或者密码错误，请重新输入");
    }
}
