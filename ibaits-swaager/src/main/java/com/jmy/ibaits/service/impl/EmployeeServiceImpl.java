package com.jmy.ibaits.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmy.ibaits.mapper.EmployeeMapper;
import com.jmy.ibaits.model.EmployeeDO;
import com.jmy.ibaits.model.param.EmpVO;
import com.jmy.ibaits.model.param.EmployeeParam;
import com.jmy.ibaits.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @SuppressWarnings("unchecked")
    @DS("mysql_2")
    @Override
    public List<Map<String,Object>> selectByCondition() throws JsonProcessingException {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from sys_user");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(maps);
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList = mapper.readValue(json, List.class);
        System.out.println(mapList.get(0).get("id"));
        System.out.println(mapList.get(0).get("username"));
        return maps;
    }

    @DS("mysql_3")
    @Override
    public List<Map<String,Object>> selectByCondition1() {
        return  jdbcTemplate.queryForList("select * from app_order");
    }
}
