package com.ryz.qasystem.mapper;

import com.ryz.qasystem.model.Notification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notification record);

    int insertSelective(Notification record);

    Notification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);

    List<Notification> getAllNotificationByUserIdByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("id") Integer id);

    Long getTotalNumNotificationByUserId(Integer id);

    Integer updateStatusById(Integer id);

    Integer getTotalNotificationByUserId(Integer id);
}