package com.jmy.ibaits.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel("员工信息对象")
public class EmployeeDO {
    @ApiModelProperty(value = "员工ID",name = "empId",example ="1")
    private Integer empId;
    @ApiModelProperty(value = "员工编号",name = "empno",example ="1")
    private Integer empno;
    @ApiModelProperty(value = "员工姓名",name = "name")
    private String name;
    @ApiModelProperty(value = "员工年龄",name = "age",example ="1")
    private Integer age;
    @ApiModelProperty(value = "员工所在部门名称",name = "deptName")
    private String deptName;
    @ApiModelProperty(value = "员工直属领导",name = "leader")
    private String leader;
}
