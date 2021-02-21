package com.ryz.qasystem.controller;

import com.ryz.qasystem.model.RespBean;
import com.ryz.qasystem.model.Seckill;
import com.ryz.qasystem.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    SeckillService seckillService;

    @GetMapping("/saveOrder")
    public RespBean saveOrder(@RequestParam("id") Long id, @RequestParam("userId") Integer userId){
        return seckillService.saveOrder(id, userId);
    }

    @GetMapping("/")
    public List<Seckill> getAllGoods(){
        return seckillService.getAllGoods();
    }

}
