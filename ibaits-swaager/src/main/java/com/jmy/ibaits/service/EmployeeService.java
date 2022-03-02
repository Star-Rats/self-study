package com.jmy.ibaits.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.Page;
import com.jmy.ibaits.model.EmployeeDO;
import com.jmy.ibaits.model.param.EmpVO;
import com.jmy.ibaits.model.param.EmployeeParam;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<EmployeeDO> findEmployeeByQueryParam(EmployeeParam employeeParam);

    List<Map<String, Object>> findAll();

    Integer save(EmpVO empVO);

    List<Map<String,Object>> selectByCondition() throws JsonProcessingException;

    List<Map<String,Object>> selectByCondition1();
}
