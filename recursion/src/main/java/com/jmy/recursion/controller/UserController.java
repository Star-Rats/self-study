package com.jmy.recursion.controller;

import com.jmy.recursion.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("")
    public String resultTip(){
        log.info("SQL服务启动······");
        Long startTime = System.currentTimeMillis();

        int i = 1;
        int j = 1;
        while (true) {
            if (0 == userService.executeSQL()) {
                log.info("SQL失败第{}次", i++);
                log.info("失败重连{}毫秒", UserService.TIME);
            } else {
                log.info("SQL成功第{}次",i++);
                UserService.RETRYTIME = 0L;
                UserService.TIME = 0L;
            }

        }

    }

}
