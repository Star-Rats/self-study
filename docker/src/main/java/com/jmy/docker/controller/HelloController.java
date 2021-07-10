package com.jmy.docker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    @Value("${spring.profiles.active}")
    private String active;
    @GetMapping("hello")
    public String hello(){
        log.info("hello,springboot~");
        return "hello~~" + active;
    }
}
