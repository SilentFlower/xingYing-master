package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.FeedBack;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  FeedBackService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-18
 */
public interface FeedBackService extends IService<FeedBack> {

    PageInfo<FeedBack> getListByPage(PageQueryEntity<FeedBack> params);
}
