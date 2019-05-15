package com.jinnian.framework.util.pictureUtils;


import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author liuqi
 * @description sha256加密
 * @date 2019/4/27 14:19
 */
public class SHA256 {
private static final String CHARSET_NAME = "UTF-8";

    /**
     * SHA256加密
     * @param str 明文
     * @return  密文
     */
    public static String SHA256Encode(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes(CHARSET_NAME));
            encodeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    public static void main(String[] args) {
        String name = SHA256Encode("NAME");
        //cpu核心数
        int i = Runtime.getRuntime().availableProcessors();

    }
}
