package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  OrderDetailService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
public interface OrderDetailService extends IService<OrderDetail> {

    PageInfo<OrderDetail> getListByPage(PageQueryEntity<OrderDetail> params);
}
