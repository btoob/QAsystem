package com.ryz.qasystem.controller;

import com.ryz.qasystem.Utils.AvatarUtil;
import com.ryz.qasystem.model.RespBean;
import com.ryz.qasystem.model.User;
import com.ryz.qasystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AvatarUtil avatarUtil;

    @PostMapping("/signUp")     //需要在SecurityConfig中放行该接口，不然访问不到
    public RespBean signUp(@RequestBody User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());
        user.setPassword(encode);
        String avatarUrl = avatarUtil.getAvatarData();
        user.setUserFace(avatarUrl);
        if (userService.addUser(user)==1){
            return RespBean.ok("注册成功, 请登录");
        }
        return RespBean.ok("注册失败");
    }

}
