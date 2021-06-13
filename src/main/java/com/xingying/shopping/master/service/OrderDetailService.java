package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.response.DetailsOrder;
import com.xingying.shopping.master.entity.response.OrderAndCoupon;

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

    /**
     * 获取订单详细内容（包含优惠券使用）
     * @param orderId
     * @return
     */
    DetailsOrder getAllDetails(String orderId);

    /**
     * 手动发货
     * @param orderDetail
     * @return
     */
    boolean manualDelivery(OrderAndCoupon orderDetail);
}
