package com.pins.filepublisher.service;

import cn.hutool.core.io.file.FileWriter;
import com.pins.filepublisher.model.vo.NginxConfigVO;
import com.spire.doc.*;
import com.deepoove.poi.XWPFTemplate;
import com.pins.filepublisher.model.NginxConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class NginxConfigFileService extends AbstractFileService {

    @Override
    void generatingFile(NginxConfig configVO) throws IOException {
        Map<String, Object> model = this.buildModel(configVO);

        try (XWPFTemplate template = XWPFTemplate.compile("D:\\IDEAProject\\self-study\\file-publisher\\src\\main\\resources\\file\\nginx\\nginx-config.docx",AbstractFileService.configure).render(model)) {
            FileOutputStream out = new FileOutputStream("output.docx");
            template.write(out);
        }

        // 将word的文字提取出来转换成文本文件

        //加载Word文档
        Document document = new Document();
        document.loadFromFile("output.docx");

        //获取文档中的文本保存为String
        String text=document.getText();
        FileWriter writer = new FileWriter("d://nginx.config");
        writer.write(text);

    }

    private Map<String, Object> buildModel(NginxConfig configVO) {
        Map<String, Object> model = new HashMap<>();
        model.put("port",80);
        model.put("domainName","www.jiangmingyang.com");
        model.put("url","/api");
        model.put("proxy_pass","www.baidu.com");
        return model;
    }

    public static void main(String[] args) throws IOException {
        new NginxConfigFileService().generatingFile(null);
    }

    public void addConfig(NginxConfigVO nginxConfigVO) {
        NginxConfig nginxConfig = new NginxConfig();
        BeanUtils.copyProperties(nginxConfigVO,nginxConfig);

    }
}
