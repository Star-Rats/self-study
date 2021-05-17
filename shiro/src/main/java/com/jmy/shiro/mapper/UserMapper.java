package com.jmy.shiro.mapper;

import com.jmy.shiro.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    Integer save(User user);

    User findByUsername(String username);
}
