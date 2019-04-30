package com.jinnian.framework.common.constant;

import java.io.Serializable;

public class UploadResult implements Serializable {
    private static final long serialVersionUID = -3275060008229943788L;
    private String result = "1";
    private String msg;
    private String downloadUrl = "";

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}

