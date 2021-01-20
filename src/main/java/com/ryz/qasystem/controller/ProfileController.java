package com.ryz.qasystem.controller;

import com.ryz.qasystem.model.RespPageBean;
import com.ryz.qasystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    NotificationService notificationService;
    @GetMapping("/{id}")
    public RespPageBean getAllNotificationByUserIdByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, @PathVariable Integer id){
        return notificationService.getAllNotificationByUserIdByPage(page, size, id);
    }
}
