package com.jmy.redislock.model;

import lombok.Data;

@Data
public class Prize {
    private Long id;
    private String prizeName;
    private Long stock;
}
