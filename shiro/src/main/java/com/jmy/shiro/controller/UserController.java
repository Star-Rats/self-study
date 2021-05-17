package com.jmy.shiro.controller;

import com.jmy.shiro.model.User;
import com.jmy.shiro.service.UserService;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(value = "用户模块",tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/login")
    @ApiOperation(value = "用户登陆",notes = "输入登录名和密码进行登录")
    public String login(String username, String password){
        Subject user = SecurityUtils.getSubject();
        try {
            user.login(new UsernamePasswordToken(username,password));
            return "登陆成功!";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return "用户名错误!";
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            return "密码错误!";
        }
    }

    @PostMapping("/regist")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public String regist(User user){
        try {
            userService.regist(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "注册失败";
        }

        if (null != user.getId()) {
            return "注册成功！";
        } else {
            return "注册失败";
        }
    }
}
