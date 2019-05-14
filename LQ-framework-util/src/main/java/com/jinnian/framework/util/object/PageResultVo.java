package com.jinnian.framework.util.object;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用于bootstrap table返回json格式
 * @program:springboot
 * @author:liuqi
 * @create:2019-4-20 13:49
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PageResultVo {
    private Long total;
    private List rows;

    public PageResultVo(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResultVo() {
    }
}
