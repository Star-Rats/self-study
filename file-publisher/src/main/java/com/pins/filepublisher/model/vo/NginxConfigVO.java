package com.pins.filepublisher.model.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NginxConfigVO {
    private List<String> upstream;
    private String domainName;
    private Integer port;
    private List<Map<String,Map<String,String>>> location;
}
