package com.jmy.ibaits.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jmy.ibaits.model.EmployeeDO;
import com.jmy.ibaits.model.param.EmpVO;
import com.jmy.ibaits.model.param.EmployeeParam;
import com.jmy.ibaits.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/emloyee")
@Api(value = "EmployeeController",tags = "员工信息接口")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/page")
    @ApiOperation(value = "员工信息分页查询",notes = "查询参数可选")
    public PageInfo<EmployeeDO> getEmployeeList(EmployeeParam employeeParam,
                                            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "20")Integer pageSize){

        PageHelper.startPage(pageNum, pageSize);
        Page<EmployeeDO> dos = (Page<EmployeeDO>) employeeService.findEmployeeByQueryParam(employeeParam);
        PageInfo<EmployeeDO> page = new PageInfo<EmployeeDO>(dos.getResult());
        System.out.println(dos.getTotal());
        return page;
    }

    @GetMapping("map")
    public List<Map<String,Object>> mapList(){
        return employeeService.findAll();
    }

    @PostMapping("save")
    public Integer mapList(@RequestBody EmpVO empVO){
        return employeeService.save(empVO);
    }

    @GetMapping("schedule")
    public void schedule(String name){
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        ses.scheduleAtFixedRate(() -> System.out.println("hello" + name),3,5, TimeUnit.SECONDS);
    }

    @PostMapping("ds/mysql")
    public List<Map<String,Object>> dsQuery() throws JsonProcessingException {
        return employeeService.selectByCondition();
    }

    @PostMapping("ds/sqlserver")
    public List<Map<String,Object>> dsQueryS(){
        return employeeService.selectByCondition1();
    }
}
