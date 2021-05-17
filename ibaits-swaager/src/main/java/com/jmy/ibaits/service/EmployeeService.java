package com.jmy.ibaits.service;

import com.github.pagehelper.Page;
import com.jmy.ibaits.model.EmployeeDO;
import com.jmy.ibaits.model.param.EmployeeParam;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDO> findEmployeeByQueryParam(EmployeeParam employeeParam);
}
