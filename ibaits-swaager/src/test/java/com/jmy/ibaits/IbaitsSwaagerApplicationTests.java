package com.jmy.ibaits;

import com.alibaba.fastjson.JSONObject;
import com.jmy.mapper.PresPhotos;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = IbaitsSwaagerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class IbaitsSwaagerApplicationTests {

    @Test
    void contextLoads() {
        ArrayList<PresPhotos> presPhotos = new ArrayList<>();
        PresPhotos photo = new PresPhotos();
        photo.setPres_photos("s4d56sa4d6sa4d6a5");
        presPhotos.add(photo);

        System.out.println(extractBase64FromJson(JSONObject.toJSONString(presPhotos)));
    }

    private String extractBase64FromJson(String jsonArray){
        if (StringUtils.isBlank(jsonArray)) {
            return null;
        }

        List<PresPhotos> list = JSONObject.parseArray(jsonArray, PresPhotos.class);
        return list.isEmpty() ? null : list.get(0).getPres_photos();
    }

    @Test
    public void generator(){
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("generator/generatorConfig.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(is);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
    }
}
