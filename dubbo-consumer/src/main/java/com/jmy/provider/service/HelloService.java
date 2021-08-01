package com.jmy.provider.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.context.annotation.Configuration;

@Configuration
@Service
public interface HelloService {
    String sayHello(String name);
}
