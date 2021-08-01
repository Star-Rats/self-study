package com.jmy.provider.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0")
@Component
public class DefaultHelloService implements HelloService{
    @Value("${spring.application.name}")
    private String serviceName;
    @Override
    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", serviceName, name);
    }
}
