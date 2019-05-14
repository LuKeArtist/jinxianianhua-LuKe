package com.jinnian.framework.util.pictureUtils;

import com.github.pagehelper.PageInfo;
import com.jinnian.framework.util.consts.CommonConst;
import com.jinnian.framework.util.enums.ResponseStatusEnum;
import com.jinnian.framework.util.object.PageResultVo;
import com.jinnian.framework.util.object.ResponseVo;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接口返回工具
 *
 * @author:liuqi
 * @create:2019-4-20 13:49
 **/
public class ResultHopeUtil {
    /**
     * ModelAndView
     **/
    public static ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    public static ModelAndView view(String view, Map<String, Object> model) {
        return new ModelAndView(view, model);
    }

    public static ModelAndView redirect(String view) {
        return new ModelAndView("redirect:" + view);
    }

    /**
     * vo
     **/
    public static ResponseVo vo(int code, String message, Object data) {

        return new ResponseVo<>(code, message, data);
    }


    /**
     * error
     * 失败
     **/
    public static ResponseVo error(int code, String message) {
        return vo(code, message, null);
    }

    public static ResponseVo error(ResponseStatusEnum statusEnum) {
        return vo(statusEnum.getCode(), statusEnum.getMessage(), null);
    }

    public static ResponseVo error(String message) {
        return vo(CommonConst.DEFAULT_ERROR_CODE, message, null);
    }

    /**
     * success
     * 成功
     **/
    public static ResponseVo success(String message, Object data) {
        return vo(CommonConst.DEFAULT_SUCCESS_CODE, message, data);
    }

    public static ResponseVo success(String message) {
        return success(message, null);
    }

    public static ResponseVo success(ResponseStatusEnum statusEnum) {
        return vo(statusEnum.getCode(), statusEnum.getMessage(), null);
    }

    /**
     * PageResultVo
     * 分页
     **/
    public static PageResultVo tablePage(Long total, List<?> list) {
        return new PageResultVo(total, list);
    }

    public static PageResultVo tablePage(PageInfo pageInfo) {
        if (pageInfo == null) {
            return new PageResultVo(0L, new ArrayList());
        }
        return tablePage(pageInfo.getTotal(), pageInfo.getList());
    }
}
