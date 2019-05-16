package com.jinnian.channel.mapper;

import com.framework.model.channel.entity.CodeDo;
import com.framework.model.channel.vo.ChannelCodeVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @authod liuqi
 * @date 2019/5/13 16:03
 */

@Repository
@Mapper
public interface CodeMapper {


    //注册
    ChannelCodeVo register(ChannelCodeVo channelCodeVo);

    //查询
    CodeDo findAll(CodeDo codeDo);

    //添加
    CodeDo addAll(CodeDo codeDo);


}
