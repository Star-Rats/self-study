package com.jmy.ibaits;

import com.alibaba.fastjson.JSONObject;
import com.jmy.mapper.PresPhotos;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

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

}
