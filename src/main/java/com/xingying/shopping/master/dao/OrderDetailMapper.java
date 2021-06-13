package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Coupon;
import com.xingying.shopping.master.entity.OrderDetail;
import com.xingying.shopping.master.entity.ext.GoodsAndOrderDetail;
import com.xingying.shopping.master.entity.request.OrderGoodsRes;
import com.xingying.shopping.master.entity.response.OrderAndCoupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@Repository
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    List<OrderDetail> getListByPage(OrderDetail orderDetail);

    /**
     * 尝试扣减库存
     * @param goods
     * @return
     */
    Boolean minStock(OrderGoodsRes goods);

    /**
     * 获取店铺优惠券
     * @param orderId
     * @return
     */
    List<Coupon> getShopCoupons(@Param("orderId") String orderId, @Param("flag") Integer flag);

    /**
     * 查找订单详细表及相关优惠券
     * @param orderId
     * @return
     */
    List<OrderAndCoupon> getOrderAndCoupon(String orderId);

    /**
     * 查找自动发货的商品
     * @param orderId
     */
    List<GoodsAndOrderDetail> findAutoSend(String orderId);
}
