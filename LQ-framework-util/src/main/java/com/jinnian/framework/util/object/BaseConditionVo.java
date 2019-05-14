package com.jinnian.framework.util.object;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 基础转换 其实分页工具会自动计算，只需要num和size即可
 *
 * @program:springboot
 * @author:liuqi
 * @create:2019-4-20 13:49
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseConditionVo {

    public final static int DEFAULT_PAGE_SIZE = 10;
    private int pageNum = 1;
    private int pageSize = 0;
    private int pageStart = 0;
    private String orderField;
    private String orderDirection;
    private String keywords;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    public int getPageSize() {
        return pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public int getPageStart() {
        return pageNum > 0 ? (pageNum - 1) * pageSize : 0;
    }
}
