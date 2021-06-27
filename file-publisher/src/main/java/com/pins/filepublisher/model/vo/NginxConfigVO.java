package com.pins.filepublisher.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class NginxConfigVO {
    private String domainName;
    private Integer port;
    private List<String> url;
    private List<String> proxyPass;
    private List<String> upstream;
}
