package com.jmy.ibaits.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.Page;
import com.jmy.ibaits.mapper.EmployeeMapper;
import com.jmy.ibaits.model.EmployeeDO;
import com.jmy.ibaits.model.param.EmpVO;
import com.jmy.ibaits.model.param.EmployeeParam;
import com.jmy.ibaits.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final JdbcTemplate jdbcTemplate;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper, JdbcTemplate jdbcTemplate) {
        this.employeeMapper = employeeMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

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

    @DS("mysql_2")
    @Override
    public List<Map<String,Object>> selectByCondition() {
        return  jdbcTemplate.queryForList("select * from sys_user");
    }

    @DS("mysql_3")
    @Override
    public List<Map<String,Object>> selectByCondition1() {
        return  jdbcTemplate.queryForList("select * from app_order");
    }
}
