package com.pins.filepublisher.controller;

import com.pins.filepublisher.model.vo.NginxConfigVO;
import com.pins.filepublisher.service.NginxConfigFileService;
import com.pins.filepublisher.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/nginx")
public class NginxFileController {
    @Autowired
    private NginxConfigFileService nginxConfigFileService;

    @PostMapping("/add-config")
    public Result addNginxConfig(NginxConfigVO nginxConfigVO){
        try {
            nginxConfigFileService.addConfig(nginxConfigVO);
        } catch (IOException e) {
            return new Result(Result.ERROR_CODE,"操作失败！",null);
        }
        return new Result(Result.SUCCESS_CODE,"操作成功！",null);
    }


    @GetMapping("/config-list")
    public Result listConfigs(){
        List<File> configFiles = nginxConfigFileService.listConfigFiles();
        return new Result(configFiles);
    }

    @GetMapping("/down-load")
    public void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }
}
