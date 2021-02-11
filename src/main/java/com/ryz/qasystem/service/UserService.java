package com.ryz.qasystem.service;

import com.ryz.qasystem.mapper.UserMapper;
import com.ryz.qasystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    public List<User> loadUserByNameAndPwd(String username, String password) {
        return userMapper.loadUserByNameAndPwd(username, password);
    }

    public Integer addUser(User user) {
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        return userMapper.insertSelective(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        return user;
    }
}
