package com.jmy.ibaits.service.impl;

import com.github.pagehelper.Page;
import com.jmy.ibaits.mapper.EmployeeMapper;
import com.jmy.ibaits.model.EmployeeDO;
import com.jmy.ibaits.model.param.EmpVO;
import com.jmy.ibaits.model.param.EmployeeParam;
import com.jmy.ibaits.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDO> findEmployeeByQueryParam(EmployeeParam employeeParam) {
        return employeeMapper.findEmployeeByQueryParam(employeeParam);
    }

    @Override
    public List<Map<String, Object>> findAll() {
        return employeeMapper.findAll();
    }

    @Override
    public Integer save(EmpVO empVO) {
        return employeeMapper.save(empVO);
    }
}
