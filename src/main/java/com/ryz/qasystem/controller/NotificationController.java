package com.ryz.qasystem.controller;

import com.ryz.qasystem.model.RespBean;
import com.ryz.qasystem.model.RespPageBean;
import com.ryz.qasystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/{id}")
    public RespPageBean getAllNotificationByUserIdByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, @PathVariable Integer id){
        return notificationService.getAllNotificationByUserIdByPage(page, size, id);
    }

    @PutMapping("/status/{id}")
    public RespBean doUpdate(@PathVariable Integer id){
        if (notificationService.updateStatusById(id)==1){
            return RespBean.ok("未读改为已读");
        }
        return RespBean.error("修改未读状态失败");
    }

    @GetMapping("/notificationNum/{userId}")
    public RespBean getTotalNotificationByUserId(@PathVariable Integer userId){
        Integer totalNum = notificationService.getTotalNotificationByUserId(userId);
        return RespBean.ok("成功获取通知总数", totalNum);
    }
}
