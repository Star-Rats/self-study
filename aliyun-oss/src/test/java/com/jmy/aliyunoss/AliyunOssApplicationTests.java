package com.jmy.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.URL;
import java.util.Date;

@SpringBootTest
class AliyunOssApplicationTests {

    private final String bucketName = "jiangmingyang";
    @Autowired
    private OSS ossClient;
    @Test
    void contextLoads() {
    }

    @Test
    void uploadToAliyunOSS() throws IOException {
        String filePath = "E:/dnf补丁/13865045421.JPG";
        String objectName = "jmy-document/test.jpg";
        byte[] file = fileConvertToByteArray(new File(filePath));

        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(file));

    }

    @Test
    void getFileAccessUrl() {
        String objectName = "jmy-document/test.jpg";
        // 设置URL过期时间为1小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        System.out.println(url);
    }

    /**
     * 把一个文件转化为byte字节数组。
     *
     * @return 字节数组
     */
    private byte[] fileConvertToByteArray(File file) {
        byte[] data = null;

        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray();

            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

}
