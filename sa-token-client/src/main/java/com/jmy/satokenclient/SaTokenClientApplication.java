package com.jmy.satokenclient;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SaTokenClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaTokenClientApplication.class, args);
        log.info("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }

}
