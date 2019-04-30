package com.jinnian.framework.common.aspect;

import com.alibaba.fastjson.JSONPathException;
import com.jinnian.framework.common.exception.JNException;
import com.jinnian.framework.common.response.ResultBean;
import feign.codec.DecodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author liuqi
 * @date 2019/4/13
 * 统一异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResultBean<Object> exceptionHandler(Throwable e) {

        return doGlobalExceptionHandler(e);
    }

    /**
     * 执行异常处理
     *
     * @param e
     * @return
     */
    public static ResultBean<Object> doGlobalExceptionHandler(Throwable e) {

        log.error(e.getMessage(), e);

        if (e instanceof JNException) {
            Integer code = ((JNException) e).getCode();
            String msg = ((JNException) e).getMsg();
            if (null == code) {
                return ResultBean.ofError(msg);
            } else {
                return ResultBean.ofError(code, msg);
            }
        } else if (e instanceof MissingServletRequestParameterException) {
            String parameterName = ((MissingServletRequestParameterException) e).getParameterName();
            return ResultBean.ofError(parameterName + "不能为空");
        } else if (e instanceof MethodArgumentNotValidException) {
            return ResultBean.ofError("表单必录数据填写不完整");
        } else if (e instanceof DecodeException) {
            return ResultBean.ofError(e.getMessage());
        } else if (e instanceof IllegalArgumentException) {
            return ResultBean.ofError(e.getMessage());
        } else if (e instanceof NullPointerException) {
            return ResultBean.ofError(e.getMessage());
        } else if (e instanceof NumberFormatException) {
            return ResultBean.ofError("参数类型转换异常");
//        } else if (e instanceof JsonParseException) {
//            return ResultBean.ofError("参数类型转换异常");
        } else if (e instanceof JSONPathException) {
            return ResultBean.ofError("类型转换异常");
        } else if (e instanceof BadSqlGrammarException) {
            return ResultBean.ofError("出错啦,请稍后再试!");
        } else if (e instanceof RuntimeException) {
            return ResultBean.ofError("出错啦,请稍后再试!");
        } else if (e instanceof Exception) {
            return ResultBean.ofError("出错啦,请稍后再试!");
        } else {
            String errorMsg = e.toString() == null ? e.getMessage() : e.toString();
            return ResultBean.ofError(StringUtils.isBlank(errorMsg) ? "未知错误" : errorMsg);
        }
    }

}
