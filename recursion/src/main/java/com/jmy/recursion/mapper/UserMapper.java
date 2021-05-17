package com.jmy.recursion.mapper;

import com.jmy.recursion.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    void save(User user);
}
