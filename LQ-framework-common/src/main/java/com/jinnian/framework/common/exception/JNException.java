package com.jinnian.framework.common.exception;


import com.jinnian.framework.common.enums.BaseExceptionEnum;

/**
 * 自定义jinnian系统异常
 *
 * @author liuqi
 * @date 2019/4/13
 */
public class JNException extends RuntimeException {

    private int code;

    private String msg;


    public JNException() {
        super();
    }

    public JNException(Throwable cause) {
        super(cause);
    }

    public JNException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public JNException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public JNException(BaseExceptionEnum exEnum) {
        this.code = exEnum.getCode();
        this.msg = exEnum.getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
