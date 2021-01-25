package com.ryz.qasystem.service;

import com.ryz.qasystem.mapper.NotificationMapper;
import com.ryz.qasystem.model.Notification;
import com.ryz.qasystem.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    public RespPageBean getAllNotificationByUserIdByPage(Integer page, Integer size, Integer id) {
        if (page!=null&&size!=null){
            page = (page-1)*size;
        }
        List<Notification> allNotificationByUserIdByPage = notificationMapper.getAllNotificationByUserIdByPage(page, size, id);
        Long totalNumNotification = notificationMapper.getTotalNumNotificationByUserId(id);

        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(allNotificationByUserIdByPage);
        pageBean.setTotal(totalNumNotification);
        return pageBean;
    }

    public Integer updateStatusById(Integer id) {
        return notificationMapper.updateStatusById(id);
    }

    public Integer getTotalNotificationByUserId(Integer id) {
        return notificationMapper.getTotalNotificationByUserId(id);
    }
}
