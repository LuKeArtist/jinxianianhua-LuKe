package com.jinnian.framework.common.constant;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;

public class DataConvertHelper {
    public static Map<String, Object> getRequestParams(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
        HashMap<String, Object> params = new HashMap();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            params.put(name, request.getParameter(name).trim());
        }
        return params;
    }

    public static Map<String, Object> getRequestParamsNoToken(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
        HashMap<String, Object> params = new HashMap();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            params.put(name, request.getParameter(name));
        }
        return params;
    }

    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    public static boolean isNotEmpty(String str) {
        if ((str != null) && (str.length() != 0)) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String str) {
        if ((str != null) && (str.length() != 0)) {
            return false;
        }
        return true;
    }

    public static String md5Password(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            for (byte b : result) {
                int number = b & 0xFF;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getRandomString(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getSecretKey(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getXxRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandNum(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRequestXML(SortedMap<Object, Object> parame) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<xml>");
        Set set = parame.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if ("sign".equalsIgnoreCase(key)) {
                buffer.append("<" + key + ">" + "<![CDATA[" + value + "]]>" + "</" + key + ">");
            } else {
                buffer.append("<" + key + ">" + value + "</" + key + ">");
            }
        }
        buffer.append("</xml>");
        return buffer.toString();
    }


}
