package com.ryz.qasystem.mapper;

import com.ryz.qasystem.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> loadUserByNameAndPwd(String username, String password);

    User getUserById(Integer commentator);
}