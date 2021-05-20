package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  MessageService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-18
 */
public interface MessageService extends IService<Message> {

    PageInfo<Message> getListByPage(PageQueryEntity<Message> params);
}
