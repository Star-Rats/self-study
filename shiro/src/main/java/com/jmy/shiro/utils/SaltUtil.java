package com.jmy.shiro.utils;

import java.util.Random;

public class SaltUtil {

    /**
     * 获取生成的随机盐
     * @param offset 随机盐位数
     * @return 生成的随机盐
     */
    public static String getSalt(int offset){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offset; i++) {
            sb.append(chars[new Random().nextInt(chars.length)]);
        }

        return sb.toString();
    }
}
