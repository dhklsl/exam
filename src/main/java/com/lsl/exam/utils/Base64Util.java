package com.lsl.exam.utils;

import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.Base64;

public class Base64Util {

    /**
     * base64加密
     * @param str
     * @return
     */
    public static String encoder(String str){
        byte[] strByte = str.getBytes();
        String result = Base64.getEncoder().encodeToString(strByte);
        return result;
    }

    /**
     * base64解密
     * @param str
     * @return
     */
    public static String decode(String str){
        String result = "";
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] strBytes = decoder.decodeBuffer(str);
            result = new String(strBytes);

            boolean flag = StrUtil.isMessyCode(result);
            if (flag){
                return "";
            }

        } catch (IOException e) {

        }
        return result;
    }
}
