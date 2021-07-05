package com.jmy.aliyunoss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunOSSConfig {
    private final AliyunOSSProperties aliyunOSSProperties;

    public AliyunOSSConfig(AliyunOSSProperties aliyunOSSProperties) {
        this.aliyunOSSProperties = aliyunOSSProperties;
    }

    @Bean
    public OSS ossClient(){
        return new OSSClientBuilder().build(
                aliyunOSSProperties.getEndpoint(),
                aliyunOSSProperties.getAccessKeyId(),
                aliyunOSSProperties.getAccessKeySecret());
    }
}
