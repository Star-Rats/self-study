package com.jmy.aliyunoss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("aliyun")
@Component
@Data
public class AliyunOSSProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
}
