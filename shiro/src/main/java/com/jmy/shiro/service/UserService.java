package com.jmy.shiro.service;

import com.jmy.shiro.mapper.UserMapper;
import com.jmy.shiro.model.User;
import com.jmy.shiro.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Integer regist(User user) {
        // 获取生成的随机盐，并使用MD5工具类进行Hash散列加密密码
        String salt = SaltUtil.getSalt(8);
        user.setSalt(salt);
        Md5Hash pwd = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(pwd.toHex());
        return userMapper.save(user);
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
