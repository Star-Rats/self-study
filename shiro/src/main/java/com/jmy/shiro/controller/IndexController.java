package com.jmy.shiro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
@Api(value = "首页模块",tags = "系统首页")
public class IndexController {

    @GetMapping("")
    @ApiOperation(value = "系统首页",notes = "系统首页")
    public String index(){
        return "welcome!";
    }


}
