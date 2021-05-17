package com.jmy.ibaits;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(value = "com.jmy.ibaits.mapper")
@EnableSwagger2
public class IbaitsSwaagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IbaitsSwaagerApplication.class, args);
    }

}
