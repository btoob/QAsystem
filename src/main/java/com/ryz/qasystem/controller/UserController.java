package com.ryz.qasystem.controller;

import com.ryz.qasystem.model.RespBean;
import com.ryz.qasystem.model.User;
import com.ryz.qasystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public RespBean login(String username, String password){

        List<User> userList = userService.loadUserByNameAndPwd(username, password);

        if (userList != null && userList.size() == 1){

            return RespBean.ok("登录成功", userList.get(0));
        }
        return RespBean.error("用户名或者密码错误，请重新输入");
    }

    @PostMapping("/signUp")
    public RespBean signUp(@RequestBody User user){
        if (userService.addUser(user)==1){
            return RespBean.ok("注册成功, 请登录");
        }
        return RespBean.ok("注册失败");
    }

}
