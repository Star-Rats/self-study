package com.jmy.ibaits.service.impl;

import com.github.pagehelper.Page;
import com.jmy.ibaits.mapper.EmployeeMapper;
import com.jmy.ibaits.model.EmployeeDO;
import com.jmy.ibaits.model.param.EmployeeParam;
import com.jmy.ibaits.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDO> findEmployeeByQueryParam(EmployeeParam employeeParam) {
        return employeeMapper.findEmployeeByQueryParam(employeeParam);
    }
}
