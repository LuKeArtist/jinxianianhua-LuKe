package com.jinnian.channel.service;

import com.framework.model.channel.entity.CodeDo;

/**
 * @authod liuqi
 * @date 2019/5/13 16:34
 */
public interface CodeService {


    //注册
    CodeDo register(CodeDo codeDo);

    //查询
    CodeDo findAll(CodeDo codeDo);

    //添加
    CodeDo addAll(CodeDo codeDo);

}
