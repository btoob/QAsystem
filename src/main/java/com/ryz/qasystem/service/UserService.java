package com.ryz.qasystem.service;

import com.ryz.qasystem.mapper.UserMapper;
import com.ryz.qasystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public boolean loadUserByNameAndPwd(String username, String password) {
        List<User> userList =  userMapper.loadUserByNameAndPwd(username, password);
        return userList != null && userList.size() == 1;
    }
}
