package com.jinnian.framework.common.constant;

import lombok.Data;

import java.io.Serializable;

@Data
public class EntityResult<T> implements Serializable {
    private String code = "200";
    private String msg = "success";
    private T data;


}

