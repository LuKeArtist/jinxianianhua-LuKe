package com.jinnian.framework.common.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class StringHelper {
    private static final Logger logger = LoggerFactory.getLogger(StringHelper.class);
    public static final String EMPTY = "";

    public static Boolean IsEmptyOrNull(Object obj) {
        if (obj == null) {
            return Boolean.valueOf(true);
        }
        if ("".equals(obj)) {
            return Boolean.valueOf(true);
        }
        if ((obj instanceof String)) {
            if (((String) obj).length() == 0) {
                return Boolean.valueOf(true);
            }
        } else if ((obj instanceof Collection)) {
            if (((Collection) obj).size() == 0) {
                return Boolean.valueOf(true);
            }
        } else if (((obj instanceof Map)) &&
                (((Map) obj).size() == 0)) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    public static String TranString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static String TranString(Object obj, String defaultval) {
        if (obj == null) {
            return defaultval;
        }
        return obj.toString();
    }

    public static String TranSeachString(Object obj) {
        if (obj == null) {
            return "";
        }
        String result = obj.toString();

        return result.replace("\\", "\\\\").replace("_", "\\_").replace("%", "\\%");
    }

    public static String ByteToString(byte[] srcobj) {
        return ByteToString(srcobj, "UTF-16LE");
    }

    public static String ByteToString(byte[] srcObj, String charEncode) {
        String destObj = null;
        try {
            destObj = new String(srcObj, charEncode);
        } catch (Exception e) {
            logger.error("转换错误：" + e.getMessage());
        }
        return destObj.replaceAll("", " ");
    }

    public static String GetGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 使用递归算法，获取第一个随机数不为0
     *
     * @return int
     */
    public static int getRandom() {
        int number = new Random().nextInt(10);
        if (0 == number) {
            return getRandom();
        }
        return number;
    }


}

