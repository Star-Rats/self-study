package com.jmy.ibaits.model.param;

import lombok.Data;

import java.util.List;

@Data
public class EmpVO {
    private List<String> columns;
    private List<Object> values;
    private String table;
}
