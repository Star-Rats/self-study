package com.pins.filepublisher.controller;

import com.pins.filepublisher.model.vo.NginxConfigVO;
import com.pins.filepublisher.service.NginxConfigFileService;
import com.pins.filepublisher.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nginx")
public class NginxFileController {
    @Autowired
    private NginxConfigFileService nginxConfigFileService;

    @PostMapping("/add-config")
    public Result addNginxConfig(NginxConfigVO nginxConfigVO){
        nginxConfigFileService.addConfig(nginxConfigVO);
        return new Result(Result.SUCCESS_CODE,"操作成功！",null);
    }
}
