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

    public List<User> loadUserByNameAndPwd(String username, String password) {
        return userMapper.loadUserByNameAndPwd(username, password);
    }
}
