package com.jmy.recursion.service;

import com.jmy.recursion.mapper.UserMapper;
import com.jmy.recursion.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {

    public static Long RETRYTIME = 0L;
    public static Long TIME = 0L;

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = {Exception.class})
    public Integer executeSQL() {

        try {
            RETRYTIME = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(4);
            this.getDateSource().forEach(
                    user -> userMapper.save(user)
            );
            log.info("SQL本次耗时{}毫秒", System.currentTimeMillis() - RETRYTIME);
        } catch (Exception e) {
            TIME  += System.currentTimeMillis() - RETRYTIME;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return 0;
        }
        return 1;
    }


    private List<User> getDateSource(){
        ArrayList<User> users = new ArrayList<>(10);
        User user = new User();
        for (int i = 0; i < 10; i++) {
            user.setUsername("user" + i);
            user.setPassword("password" + i);
            users.add(user);
        }
        return users;
    }
}
