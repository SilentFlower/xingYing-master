package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Message;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-18
 */
@Repository
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> getListByPage(Message message);
}
