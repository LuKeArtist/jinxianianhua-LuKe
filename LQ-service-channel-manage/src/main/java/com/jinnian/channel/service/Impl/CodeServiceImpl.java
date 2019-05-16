package com.jinnian.channel.service.Impl;

import com.framework.model.channel.entity.CodeDo;
import com.framework.model.channel.vo.ChannelCodeVo;
import com.jinnian.channel.mapper.CodeMapper;
import com.jinnian.channel.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @authod liuqi
 * @date 2019/5/13 16:35
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;

    /**
     * 注册
     * @param channelCodeVo
     * @return
     */
    @Override
    public ChannelCodeVo register(ChannelCodeVo channelCodeVo) {

        return codeMapper.register(channelCodeVo);
    }

    /**
     * 查询
     * @param codeDo
     * @return
     */
    @Override
    public CodeDo findAll(CodeDo codeDo) {

        return codeMapper.findAll(codeDo);
    }


    /**
     * 添加
     * @param codeDo
     * @return
     */
    @Override
    public CodeDo addAll(CodeDo codeDo) {

        return codeMapper.addAll(codeDo);
    }

}
