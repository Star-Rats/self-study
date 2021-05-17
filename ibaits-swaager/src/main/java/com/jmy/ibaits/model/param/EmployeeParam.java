package com.jmy.ibaits.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("员工信息查询参数")
public class EmployeeParam {
    @ApiModelProperty(value = "员工姓名",name = "name")
    private String name;
    @ApiModelProperty(value = "员工年龄",name = "age",example ="18")
    private Integer age;
}
