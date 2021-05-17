package com.jmy.ibaits.mapper;

import com.github.pagehelper.Page;
import com.jmy.ibaits.model.EmployeeDO;
import com.jmy.ibaits.model.param.EmployeeParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<EmployeeDO> findEmployeeByQueryParam(@Param("emp") EmployeeParam employeeParam);
}
