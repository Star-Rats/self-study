package com.jmy.ibaits.mapper;

import com.github.pagehelper.Page;
import com.jmy.ibaits.model.EmployeeDO;
import com.jmy.ibaits.model.param.EmpVO;
import com.jmy.ibaits.model.param.EmployeeParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {
    List<EmployeeDO> findEmployeeByQueryParam(@Param("emp") EmployeeParam employeeParam);

    @MapKey("id")
    List<Map<String, Object>> findAll();

    Integer save(@Param("emp") EmpVO empVO);
}
