package com.jinnian.channel.service;

import com.framework.model.channel.entity.CodeDo;
import com.framework.model.channel.vo.ChannelCodeVo;

/**
 * @authod liuqi
 * @date 2019/5/13 16:34
 */
public interface CodeService {


    //注册
    ChannelCodeVo register(ChannelCodeVo channelCodeVo);

    //查询
    CodeDo findAll(CodeDo codeDo);

    //添加
    CodeDo addAll(CodeDo codeDo);

}
