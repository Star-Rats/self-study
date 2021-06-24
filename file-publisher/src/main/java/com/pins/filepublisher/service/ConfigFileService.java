package com.pins.filepublisher.service;

import cn.hutool.core.io.file.FileWriter;
import com.deepoove.poi.XWPFTemplate;
import com.pins.filepublisher.model.vo.NginxConfigVO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConfigFileService extends AbstractFileService {

    @Override
    void generatingFile(NginxConfigVO configVO) throws IOException {
        Map<String, Object> model = this.buildModel(configVO);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        try (XWPFTemplate template = XWPFTemplate.compile("D:\\IDEAProject\\self-study\\file-publisher\\src\\main\\resources\\file\\nginx\\nginx-config.docx",AbstractFileService.configure).render(model)) {
            template.write(byteArrayOutputStream);
        }

        FileWriter writer = new FileWriter("D:/nginx.txt");
        writer.write(byteArrayOutputStream.toString("utf-8"));

    }

    private Map<String, Object> buildModel(NginxConfigVO configVO) {
        Map<String, Object> model = new HashMap<>();
        model.put("port",80);
        model.put("domainName","www.jiangmingyang.com");
        model.put("url","/api");
        model.put("proxy_pass","www.baidu.com");
        return model;
    }

    public static void main(String[] args) throws IOException {
        new ConfigFileService().generatingFile(null);
    }
}
