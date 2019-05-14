package com.jinnian.framework.util.object;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jinnian.framework.util.enums.ResponseStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;

/**
 * 使用阿里的fastjson
 *
 * @program:springboot
 * @author:liuqi
 * @create:2019-4-20 13:49
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseVo<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseVo(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseVo(ResponseStatusEnum status, T data) {

        this(status.getCode(), status.getMessage(), data);
    }

    public String toJson() {
        T t = this.getDat();
        if (t instanceof List || t instanceof Collection) {
            return JSONObject.toJSONString(this, SerializerFeature.WriteNullListAsEmpty);
        } else {
            return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
        }
    }

    private T getDat() {
        return null;
    }
}
