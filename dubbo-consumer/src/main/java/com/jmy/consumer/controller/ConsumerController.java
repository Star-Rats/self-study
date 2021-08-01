package com.jmy.consumer.controller;

import com.jmy.provider.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Reference(version = "1.0.0",check = false)
    private HelloService helloService;

    @GetMapping("hello")
    public String sayHello(String name){
        return helloService.sayHello(name);
    }
}
