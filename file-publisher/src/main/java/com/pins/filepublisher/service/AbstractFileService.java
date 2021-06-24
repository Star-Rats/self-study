package com.pins.filepublisher.service;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.PictureRenderPolicy;
import com.pins.filepublisher.model.vo.NginxConfigVO;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public abstract class AbstractFileService {
    /**
     *  获取项目中文件，不存在返回null
     * @param fileName 文件路径（相对路径）
     * @return
     */
    public static InputStream getServiceFile(String fileName){
        try {
            ClassPathResource classPathResource = new ClassPathResource(fileName);
            return classPathResource.getInputStream();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    protected static Configure configure = Configure.newBuilder()
            .buildGramer("${", "}")
            .setElMode(Configure.ELMode.SPEL_MODE)
            .buildGrammerRegex("[\\w]+(\\.[\\w]+)*")
            .setValidErrorHandler(new Configure.ClearHandler())
            .addPlugin('@', new PictureRenderPolicy())
            .build();
    
    abstract void generatingFile(NginxConfigVO configVO) throws IOException;

}
