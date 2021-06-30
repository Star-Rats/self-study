package com.pins.filepublisher.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import com.pins.filepublisher.model.NginxConfigDTO;
import com.pins.filepublisher.model.vo.NginxConfigVO;
import com.spire.doc.*;
import com.deepoove.poi.XWPFTemplate;
import com.pins.filepublisher.model.NginxConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class NginxConfigFileService extends AbstractFileService {
    public final static String CONFIGFILEPATH = "D:\\pins\\hospital-manage-2020-server\\src\\main\\java\\com\\pinshealth\\manage\\controller";
    @Autowired
    private ShellService shellService;

    @Override
    void generatingFile(NginxConfig configVO) throws IOException {
        if (configVO == null) {
            return;
        }

        Map<String, Object> model = this.buildLocationConfig(configVO.getLocation());
        model.put("port", configVO.getPort());
        model.put("domainName", configVO.getDomainName());

        //String filePath = "E:\\open-source\\self-study\\file-publisher\\src\\main\\resources\\file\\nginx\\nginx-config.docx";
        String filePath = "/tmp/nginx-config.docx";

        try (XWPFTemplate template = XWPFTemplate.compile(filePath,AbstractFileService.configure).render(model)) {
            template.write(new FileOutputStream("/tmp/output.docx"));
        }
        //加载Word文档
        Document document = new Document();
        document.loadFromFile("/tmp/output.docx");

        //获取文档中的文本保存为String
        String text=document.getText();

        String configFileName = configVO.getDomainName();
        FileWriter writer = new FileWriter("/usr/nginx/conf-custom/" + configFileName + ".conf");
        writer.write(text);

        // 重启Nginx
        shellService.restartNginxService("systemctl restart nginx.service");

    }

    private Map<String, Object> buildLocationConfig(List<Map<String,Map<String,String>>> location) {
        ArrayList<NginxConfigDTO> list = new ArrayList<>();
        Map<String, Object> model = new HashMap<>();
        location.forEach(config -> {
            String url = (String) config.keySet().toArray()[0];
            String nginxConfig = this.createNginxConfig(url, config.get(url).get("proxy_pass"));
            NginxConfigDTO nc = new NginxConfigDTO();
            nc.setConfig(nginxConfig);
            list.add(nc);
        });

        model.put("config",list);
        return model;
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
        File[] ls = FileUtil.ls(CONFIGFILEPATH);
        Arrays.stream(ls).map(File::getName).forEach(System.out::println);
        System.exit(0);
    }

    public void addConfig(NginxConfigVO nginxConfigVO) throws IOException {
        NginxConfig nginxConfig = new NginxConfig();
        BeanUtils.copyProperties(nginxConfigVO,nginxConfig);

        List<Map<String, Map<String, String>>> location = new ArrayList<>();
        List<String> urls = nginxConfigVO.getUrl();
        if (!CollectionUtils.isEmpty(urls)) {
            for (int i = 0; i < urls.size(); i++) {
                HashMap<String, Map<String, String>> urlMap = new HashMap<>();
                HashMap<String, String> proxyMap = new HashMap<>();

                proxyMap.put("proxy_pass",nginxConfigVO.getProxyPass().get(i));

                urlMap.put(urls.get(i), proxyMap);
                location.add(urlMap);
            }
        }

        nginxConfig.setLocation(location);
        this.generatingFile(nginxConfig);
    }

    public List<File> listConfigFiles() {
        File[] ls = FileUtil.ls(CONFIGFILEPATH);
        return Arrays.asList(ls);
    }

    private String createNginxConfig(String location,String proxy_pass){
        return "    location " + location + " {\n" +
                "        proxy_pass " + proxy_pass + ";\n" +
                "        proxy_redirect default;\n" +
                "    }\n" + "\n";
    }

    private String createStaticNginxConfig(String location,String root,String index){
        return "    location " + location + " {\n" +
                "        root " + root + ";\n" +
                "        index " + index + ";\n" +
                "    }\n";
    }
}
