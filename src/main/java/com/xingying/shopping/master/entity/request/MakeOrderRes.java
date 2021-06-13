package com.xingying.shopping.master.entity.request;

import com.xingying.shopping.master.entity.Coupon;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author SiletFlower
 * @date 2021/6/11 13:42:30
 * 用于结算时生成订单
 * @description
 */
public class MakeOrderRes implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 优惠券
     */
    Map<String, List<Coupon>> couponMap;

    /**
     * 商品
     */
    Map<String, List<OrderGoodsRes>> goodsMap;

    public Map<String, List<Coupon>> getCouponMap() {
        return couponMap;
    }

    public void setCouponMap(Map<String, List<Coupon>> couponMap) {
        this.couponMap = couponMap;
    }

    public Map<String, List<OrderGoodsRes>> getGoodsMap() {
        return goodsMap;
    }

    public void setGoodsMap(Map<String, List<OrderGoodsRes>> goodsMap) {
        this.goodsMap = goodsMap;
    }
}
